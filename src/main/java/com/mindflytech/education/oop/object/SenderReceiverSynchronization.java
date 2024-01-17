package com.mindflytech.education.oop.object;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicBoolean;

//based on https://www.baeldung.com/java-wait-notify
@Log4j2
public class SenderReceiverSynchronization {
    private final Data data;

    public SenderReceiverSynchronization(Data data) {
        this.data = data;
    }

    public static void main(String[] args) throws InterruptedException {
        SenderReceiverSynchronization sync = new SenderReceiverSynchronization(new Data());
//        sync.oneSenderOneReceiverVirtualThreads();
        sync.oneSenderOneReceiverPlatformThreads();
//        sync.twoSenderTwoReceiverVirtualThreads();
//        sync.oneSenderTwoReceiverVirtualThreads();
//        sync.twoSenderOneReceiverVirtualThreads();
    }

    private void oneSenderOneReceiverVirtualThreads() throws InterruptedException {
        String packet = "test-packet";
        log.info("-- test, before starting threads");
        sendPacketWithVirtualThread(packet, "sender");
        receivePacketWithVirtualThread("receiver");
        log.info("-- test, after starting threads, test will finish");
        Thread.sleep(2000);
    }

    private void receivePacketWithVirtualThread(String receiverName) {
        Thread.ofVirtual()
                .name(receiverName)
                .start(() -> data.receive());
    }

    private void sendPacketWithVirtualThread(String packet, String senderName) {
        Thread.ofVirtual()
                .name(senderName)
                .start(() -> data.send(packet));
    }

    private void oneSenderOneReceiverPlatformThreads() throws InterruptedException {
        String packet = "test-packet";
        log.info("-- test, before starting threads");
        Thread.ofPlatform()
                .name("sender")
                .start(() -> data.send(packet));
        Thread.ofPlatform()
                .name("receiver")
                .start(() -> data.receive());
        log.info("-- test, after starting threads, test will finish");
        Thread.sleep(2000);
    }

    private void twoSenderTwoReceiverVirtualThreads() throws InterruptedException {
        String packetOne = "test-packet-1";
        String packetTwo = "test-packet-2";
        log.info("-- test, before starting threads");
        Thread.ofVirtual()
                .name("sender-1")
                .start(() -> data.send(packetOne));
        Thread.ofVirtual()
                .name("sender-2")
                .start(() -> data.send(packetTwo));
        Thread.ofVirtual()
                .name("receiver-1")
                .start(() -> data.receive());
        Thread.ofVirtual()
                .name("receiver-2")
                .start(() -> data.receive());
        log.info("-- test, after starting threads, test will finish");
        Thread.sleep(2000);
    }

    private void oneSenderTwoReceiverVirtualThreads() throws InterruptedException {
        String packetOne = "test-packet-1";
        log.info("-- test, before starting threads");
        Thread.ofVirtual()
                .name("sender")
                .start(() -> data.send(packetOne));
        Thread.ofVirtual()
                .name("receiver-1")
                .start(() -> data.receive());
        Thread.ofVirtual()
                .name("receiver-2")
                .start(() -> data.receive());
        log.info("-- test, after starting threads, test will finish");
        Thread.sleep(2000);
    }

    private void twoSenderOneReceiverVirtualThreads() throws InterruptedException {
        String packetOne = "test-packet-1";
        String packetTwo = "test-packet-2";
        log.info("-- test, before starting threads");
        sendPacketWithVirtualThread(packetOne, "sender-1");
        sendPacketWithVirtualThread(packetTwo, "sender-2");
        receivePacketWithVirtualThread("receiver-1");
        log.info("-- test, after starting threads, test will finish");
        Thread.sleep(2000);
        System.out.println("-- data's status: " + data);
    }
}

@Log4j2
class Data {
    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private AtomicBoolean transfer = new AtomicBoolean(true);

    public synchronized String receive() {
        log.info("-- receiver started");
        while (transfer.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("-- Receiver Thread Interrupted!");
            }
        }
        transfer.set(true);

        String returnPacket = packet;
        log.info("-- receiver received packet: '" + returnPacket + "', will notifyAll()");
        notifyAll();
        log.info("-- receiver notified all, exiting with packet: '" + returnPacket + "'");
        return returnPacket;
    }

    public synchronized void send(String packet) {
        log.info("-- send started, packet: '" + packet + "'");
        while (!transfer.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("-- Sender Thread Interrupted!");
            }
        }
        transfer.set(false);

        this.packet = packet;
        log.info("-- sender sent packet: '" + packet + "', will notifyAll()");
        notifyAll();
        log.info("-- sender of packet: '" + packet + "' notified all, will exit");
    }

    @Override
    public String toString() {
        return "Data{" +
                "packet='" + packet + '\'' +
                ", transfer=" + transfer +
                '}';
    }
}
