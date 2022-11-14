import java.util.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class BerkeleyMaster 
{
    
    public Process process;

    public static void main (String [] args)  throws IOException
    {
        
        // fazer a base da comunicação distribuída entre processos aqui??
        
        Class clazz = FileOperationsTest.class;
        InputStream inputStream = clazz.getResourceAsStream("/Configuracoes1.txt");
        configProcess(inputStream, args[0]);
        

        // fazer sorteio de eventos locais/mensagens

        
        
        // ver como fazer o recebimento de mensagens

    }

    public static void configProcess(InputStream inputStream, int id){
        /* 
         * Ler arquivo e pegar conteúdo para configurar o processo
        */

        throws IOException {
            StringBuilder resultStringBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(line[0]==id){
                        break;
                    }else{
                        line = br.readLine();
                    }
                }
            }
        }
        
        process = new Process{id, line[1], line[2], line[3], line[4], line[5], line[6]};


    }

    public static void localEvent(Process p){
        // Evento local: i [c,c,c,c,...] L, onde i é o ID do nodo local e 
        // [c,c,c,c,...] é o valor do relógio vetorial local;
        p.increaseClock();
        System.out.print(p.id);
        p.toString;
        System.out.print(" L");
        System.out.println();
    
    }

    public static void sendMessage(Process pSender, Process pReceiver){
        // Envio de mensagem: i [c,c,c,c,...] S d, onde i é o ID do nodo local,
        // [c,c,c,c,...] é o valor do relógio vetorial enviado e d é o ID do nodo
        // destinatário da mensagem
        pSender.increaseClock();
        pSender.updCV(pSender.id, pSender.localClock);
        System.out.print(pSender.id);
        pSender.toString;
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
        pReceiver.updCV(pSender.id, pSender.localClock);
        pSender.toString;     
        System.out.print(" R");
        System.out.print(pSender.id);
        System.out.print(pSender.localClock)    //-> ver de onde tem q sair esse t
        System.out.println();
    }
}