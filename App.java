import java.util.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class BerkeleyMaster 
{
    
    public static void main (String [] args)  throws IOException
    {
        
        // fazer a base da comunicação entre processos aqui??
        
    }

    public static void localEvent(Process p){
        p.increaseClock();
        // Evento local: i [c,c,c,c,...] L, onde i é o ID do nodo local e 
        // [c,c,c,c,...] é o valor do relógio vetorial local;
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
        //pSender.toString;     //->> tem que ser atualizado antes
        System.out.print(" R");
        System.out.print(pSender.id);
        //System.out.print()    //-> ver de onde tem q sair esse t
        System.out.println();
    }
}