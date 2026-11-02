// bridge.js: the page's connection to the Java host.
//
// The page calls the host through the two functions below. Their names match the calls the page was written with,
// invoke and listen, so the rest of the page is the same as in the C runner.
//
// The transport is JCEF's query channel. The page sends a JSON string with window.cefQuery. The host answers by
// running window.__hostMessage with a JSON string, which is the same reply and event shape the C host sends.
//
// Messages from the host are one of two kinds:
//   { "kind": "reply", "id": 7, "result": ... }   or   { "kind": "reply", "id": 7, "error": "text" }
//   { "kind": "event", "event": "run-output", "payload": ... }

const pending = new Map();
const listeners = new Map();
let nextId = 1;

/** Called by the host. The text is one JSON message, in the shape described above. */
window.__hostMessage = (text) => {
  const message = typeof text === "string" ? JSON.parse(text) : text;

  if (message.kind === "reply") {
    const waiter = pending.get(message.id);
    if (!waiter) return;
    pending.delete(message.id);
    // Errors arrive as plain text, and are rejected as text.
    if (message.error !== undefined) {
      waiter.reject(message.error);
    } else {
      waiter.resolve(message.result);
    }
    return;
  }

  if (message.kind === "event") {
    for (const handler of listeners.get(message.event) ?? []) {
      handler({ payload: message.payload });
    }
  }
};

/** Calls a command in the host, and resolves with its result. */
export function invoke(command, args = {}) {
  return new Promise((resolve, reject) => {
    const id = nextId++;
    pending.set(id, { resolve, reject });
    const request = JSON.stringify({ kind: "command", id, command, args });
    window.cefQuery({
      request,
      persistent: false,
      // The host sends the reply through __hostMessage, so the query's own success and failure callbacks are not used.
      onSuccess: () => {},
      onFailure: (code, text) => {
        pending.delete(id);
        reject(text);
      },
    });
  });
}

/** Calls handler(event) each time the host emits the named event. The event's data is in event.payload. */
export function listen(event, handler) {
  if (!listeners.has(event)) listeners.set(event, []);
  listeners.get(event).push(handler);
  return () => {
    const list = listeners.get(event) ?? [];
    listeners.set(event, list.filter((existing) => existing !== handler));
  };
}
