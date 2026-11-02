package com.databankimx.training.runner;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

/**
 * Serves the page folder on 127.0.0.1. The page's scripts are ES modules, and a browser refuses to load modules
 * from file:// addresses, so the page is served over HTTP, as the C runner's virtual host mapping does under WebView2.
 */
final class WebServer {

    /**
     * The fixed port the page is served on. The browser keeps the page's saved data (quiz answers, theme, and
     * preferences) per address, so the port must not change between runs.
     */
    static final int PORT = 47800;

    private final HttpServer server;

    WebServer(Path root) throws IOException {
        Path base = root.toAbsolutePath().normalize();
        server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), PORT), 0);
        server.createContext("/", exchange -> serve(exchange, base));
        server.start();
    }

    /** The address of the served page, such as http://127.0.0.1:53124/index.html. */
    String pageUrl(String page) {
        return "http://127.0.0.1:" + server.getAddress().getPort() + "/" + page;
    }

    private static void serve(HttpExchange exchange, Path base) throws IOException {
        String requested = exchange.getRequestURI().getPath();
        if (requested.equals("/")) {
            requested = "/index.html";
        }
        // Resolve the request inside the page folder only, so a request cannot reach other files.
        Path file = base.resolve(requested.substring(1)).normalize();
        if (!file.startsWith(base) || !Files.isRegularFile(file)) {
            exchange.sendResponseHeaders(404, -1);
            exchange.close();
            return;
        }
        byte[] body = Files.readAllBytes(file);
        exchange.getResponseHeaders().set("Content-Type", contentType(file));
        exchange.sendResponseHeaders(200, body.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(body);
        }
    }

    private static String contentType(Path file) {
        String name = file.getFileName().toString().toLowerCase(Locale.ROOT);
        if (name.endsWith(".html")) return "text/html; charset=utf-8";
        if (name.endsWith(".js")) return "text/javascript; charset=utf-8";
        if (name.endsWith(".css")) return "text/css; charset=utf-8";
        if (name.endsWith(".svg")) return "image/svg+xml";
        if (name.endsWith(".json")) return "application/json; charset=utf-8";
        return "application/octet-stream";
    }
}
