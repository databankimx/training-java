import { isTrackpad } from "./wheel.js";

const MIN_SIZE = 8;
const MAX_SIZE = 40;

/**
 * Lets Ctrl + mouse wheel change the text size inside one pane. The pane's text reads
 * its size from the CSS variable `--<name>-size`.
 */
export function enableZoom(pane, name, defaultSize) {
  let size = defaultSize;
  pane.style.setProperty(`--${name}-size`, `${size}px`);
  pane.addEventListener(
    "wheel",
    (event) => {
      if (!event.ctrlKey) return;
      event.preventDefault();
      // Zoom in when the wheel moves up. A mouse reports that movement with the opposite sign, as the scrolling does.
      const up = isTrackpad() ? event.deltaY < 0 : event.deltaY > 0;
      size = Math.min(Math.max(size + (up ? 1 : -1), MIN_SIZE), MAX_SIZE);
      pane.style.setProperty(`--${name}-size`, `${size}px`);
    },
    { passive: false },
  );
}

/** Stops Ctrl + wheel from zooming the whole page, so only the panes change size. */
export function blockPageZoom() {
  document.addEventListener(
    "wheel",
    (event) => {
      if (event.ctrlKey) event.preventDefault();
    },
    { passive: false },
  );
}
