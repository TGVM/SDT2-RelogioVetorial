import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class App 
{
    
    public static Process process;
    static final int port = 7000;
    static final String group = "224.255.255.1";
    

    public static void main (String [] args)  throws IOException
    {
        // fazer a base da comunicação distribuída entre processos aqui??
        

        File arq = new File("Configuracoes1.txt");
        configProcess(arq, Integer.parseInt(args[0]));

        System.out.println(process);
        




        //Class clazz = FileOperationsTest.class;
        //InputStream inputStream = clazz.getResourceAsStream("/Configuracoes1.txt");
        //configProcess(inputStream, args[0]);
        

        // fazer sorteio de eventos locais/mensagens
        for(int i = 0; i<process.events; i++){
            float chance = process.chance * 100;
            int rng = (int)Math.floor(Math.random()*(100-0+1)+0);
            float delay = (float) (Math.random()*(process.max_delay - process.min_delay + 1) + 0);
            
            
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if(rng < chance){
                        System.out.print("");
                        //ENVIO DE MENSAGENS

                        // ver como fazer o recebimento de mensagens
                    }else{
                        localEvent(process);
                    }
                                
                }
            }, 0, (long) delay);

        }
    }

    public static void configProcess(File arq, int id)throws IOException{
        /* 
         * Ler arquivo e pegar conteúdo para configurar o processo
        */
            Scanner in = new Scanner(arq);
            String line = "";
                while (in.hasNextLine()) {
                    line = in.nextLine();
                    if((line.charAt(0)   +"").equals(id  +"")){
                        break;
                    }
                }
        String[] dados = line.split(" ");
        
        process = new Process(id, dados[1], Integer.parseInt(dados[2]), Float.parseFloat(dados[3]), Integer.parseInt(dados[4]), Integer.parseInt(dados[5]), Integer.parseInt(dados[6]), group, port);


    }

    public class Receive extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    DatagramSocket socket = new DatagramSocket(process.port);
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    
                    
                    //String[] received = new String(packet.getData(), 0, packet.getLength()).split(";");
                    //decodificar msg recebida

                    // receiveMessage(senderId, process);  // TODO
                    socket.close();
                } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }


    static void localEvent(Process p){
        // Evento local: i [c,c,c,c,...] L, onde i é o ID do nodo local e 
        // [c,c,c,c,...] é o valor do relógio vetorial local;
        p.increaseClock();
        System.out.print(p.id);
        System.out.println(p.toString()); 
        System.out.print(" L");
        System.out.println();
    
    }

    public static void sendMessage(Process pSender, Process pReceiver){
        // Envio de mensagem: i [c,c,c,c,...] S d, onde i é o ID do nodo local,
        // [c,c,c,c,...] é o valor do relógio vetorial enviado e d é o ID do nodo
        // destinatário da mensagem
        pSender.increaseClock();
        pSender.updCV(pSender.id, pSender.getClock());
        System.out.print(pSender.id);
        System.out.println(pSender.toString()); 
        System.out.print(" S");
        System.out.print(pReceiver.id);
        System.out.println();
    }

    public static void receiveMessage(Process pSender, Process pReceiver){
        //Recebimento de mensagem: i [c,c,c,c,...] R s t, onde i é o ID do nodo
        //local, [c,c,c,c,...] é o valor do relógio vetorial depois do recebimento
        //da mensagem, s é ID do nodo remetente da mensagem e t é o valor do
        //relógio lógico recebido com a mensagem.
        System.out.print(pReceiver.id);
        pReceiver.updCV(pSender.id, pSender.getClock());
        System.out.println(pSender.toString());     
        System.out.print(" R");
        System.out.print(pSender.id);
        System.out.print(pSender.getClock());    //-> ver de onde tem q sair esse t
        System.out.println();
    }


}