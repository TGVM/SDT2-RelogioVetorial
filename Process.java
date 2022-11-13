import java.util.*;
import java.io.IOException;

public class Process 
{
    
    public int id;  // número inteiro que identifica o processo
    public String host; // hostname ou endereço IP da máquina (nodo) que executa o processo
    public int port; // número da porta que o processo vai escutar
    public float chance; //probabilidade (entre 0 e 1) da ocorrência de um evento de envio de mensagem.
    public int events; // número de eventos que serão executados nesse nodo (recomenda-se aproximadamente 100 eventos)
    public int min_delay; // tempo mínimo de intervalo entre eventos (recomenda-se valores entre 100 e 300 ms)
    public int max_delay; // tempo máximo de intervalo entre eventos (recomenda-se valores entre 350 e 750 ms)
    private int localClock; // Relógio local
    private List<int> Clocks; //Vetor com relógios de todos os processos

    public Process(int id, String host, int port, float chance, int events, int min_delay, int max_delay){
        this.id = id;
        this.host = host;
        this.port = port;
        this.chance = chance;
        this.events = events;
        this.min_delay = min_delay;
        this.max_delay = max_delay;
        this.localClock = 0;
        this.Clocks = new List<int>();
        Clocks.add(id, localClock);
    }

    public void increaseClock(){
        localClock++;
        Clocks.set(this.id, this.localClock);
    }

    public int getClock(){
        return localClock;
    }

    public List<int> getVector(){
        return Clocks;
    }

    public void updCV(int index, int t){
        Clocks.set(index, t);
    }

    public void toString(){
        System.out.print("[");
        foreach(c in Clocks){
            System.out.print(c+", ");
        }
        System.out.print("]");
    }
    
}