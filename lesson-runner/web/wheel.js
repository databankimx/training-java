// wheel.js: makes the scroll wheel behave in every scrollable pane, for a mouse or a trackpad.
//
// The off-screen browser delivers wheel events with small values. A mouse notch arrives reversed, and a trackpad
// swipe arrives correct in direction but too small. Each mode corrects its own problem:
//   - Mouse (the Trackpad box unticked): the direction is reversed and the amount scaled up.
//   - Trackpad (the Trackpad box ticked): the direction is kept and the amount scaled up.
// The page cannot tell the two apart from the event values, so the user chooses. The choice is stored in the browser.

const KEY = "java-training-trackpad";

/** How far one unit of wheel movement scrolls, in pixels. Tune these if scrolling is too fast or too slow. */
const MOUSE_SCALE = 25;
const TRACKPAD_SCALE = 25;

let trackpad = localStorage.getItem(KEY) === "1";

/** True when the Trackpad setting is on. */
export function isTrackpad() {
  return trackpad;
}

/** Turns the Trackpad setting on or off, and remembers it. */
export function setTrackpad(on) {
  trackpad = on;
  localStorage.setItem(KEY, on ? "1" : "0");
}

/**
 * Makes a pane scroll with the wheel in the expected direction and at a usable speed. Ctrl with the wheel is left
 * alone, because it changes the text size instead.
 */
export function mouseWheelFix(pane) {
  pane.addEventListener(
    "wheel",
    (event) => {
      if (event.ctrlKey || event.deltaY === 0) return;
      event.preventDefault();
      pane.scrollTop = trackpad
        ? pane.scrollTop + event.deltaY * TRACKPAD_SCALE
        : pane.scrollTop - event.deltaY * MOUSE_SCALE;
    },
    { passive: false },
  );
}
