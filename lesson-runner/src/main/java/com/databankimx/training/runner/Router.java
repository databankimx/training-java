package com.databankimx.training.runner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefQueryCallback;
import org.cef.handler.CefMessageRouterHandlerAdapter;

/**
 * Receives the page's command requests and sends each reply back through window.__hostMessage. The request and
 * reply shapes are the ones web/bridge.js describes.
 */
final class Router extends CefMessageRouterHandlerAdapter {

    private final Commands commands;
    private final Gson gson = new Gson();

    Router(Commands commands) {
        this.commands = commands;
    }

    @Override
    public boolean onQuery(CefBrowser browser, CefFrame frame, long queryId, String request, boolean persistent,
            CefQueryCallback callback) {
        JsonObject message = JsonParser.parseString(request).getAsJsonObject();
        int id = message.get("id").getAsInt();
        String command = message.get("command").getAsString();
        JsonObject args = message.has("args") && message.get("args").isJsonObject()
                ? message.getAsJsonObject("args")
                : new JsonObject();

        JsonObject reply = new JsonObject();
        reply.addProperty("kind", "reply");
        reply.addProperty("id", id);
        try {
            reply.add("result", commands.invoke(command, args));
        } catch (Exception error) {
            reply.addProperty("error", error.getMessage() == null ? error.toString() : error.getMessage());
        }
        send(browser, reply);

        // The reply travels through __hostMessage, so the query itself only needs to be acknowledged.
        callback.success("");
        return true;
    }

    /** Sends one message to the page. The JSON is a valid JavaScript object literal, so it's passed as is. */
    void send(CefBrowser browser, JsonObject message) {
        browser.executeJavaScript("window.__hostMessage(" + gson.toJson(message) + ");", browser.getURL(), 0);
    }
}
