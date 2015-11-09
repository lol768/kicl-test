package com.lol768.kicltester;

import org.kitteh.irc.client.library.Client;
import org.kitteh.irc.client.library.event.channel.ChannelJoinEvent;
import org.kitteh.irc.lib.net.engio.mbassy.listener.Handler;

public class Main {

    public static class Listener {
        @Handler
        public void onUserJoinChannel(ChannelJoinEvent event) {
            if (event.getClient().isUser(event.getUser())) { // It's me!
                event.getChannel().sendMessage("Hello world! Kitteh's here for cuddles.");
                return;
            }
            // It's not me!
            event.getChannel().sendMessage("Welcome, " + event.getUser().getNick() + "! :3");
        }
    }

    public static void main(String[] args) {
        System.out.println("Connect");
        // Calling build() starts connecting.
        Client client = Client.builder().nick("Kitteh").serverHost("127.0.0.1").serverPort(5555).listenInput(s -> System.out.println("<-- " + s)).listenOutput(s -> System.out.println("--> " + s)).build();
        client.getEventManager().registerEventListener(new Listener());
        client.addChannel("#kicl");
    }

}
