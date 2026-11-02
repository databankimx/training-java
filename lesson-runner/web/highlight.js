// highlight.js: syntax highlighting for the Source pane, the Lesson pane, and quiz questions.
//
// The highlighting itself is done by the highlight.js library, loaded from vendor/highlight by index.html. This file
// only calls the library, and switches the colour theme with the page's light or dark setting.

/**
 * Returns the source as HTML with highlight.js spans. The language is a name such as "java", "xml" (for HTML), or
 * "bash". When no language is given, the library works out the language from the code.
 */
export function highlightCode(source, language) {
  if (language && window.hljs.getLanguage(language)) {
    return window.hljs.highlight(source, { language, ignoreIllegals: true }).value;
  }
  return window.hljs.highlightAuto(source).value;
}

/** Switches the highlight.js colour theme to match the page: dark by default, light when the page is light. */
export function setHighlightTheme(light) {
  const link = document.querySelector("#hljs-theme");
  if (link) link.href = light ? "vendor/highlight/styles/vs.min.css" : "vendor/highlight/styles/vs2015.min.css";
}
