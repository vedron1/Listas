package src.baseLista.ordenada;

import java.lang.reflect.*;
import src.baseLista.BaseListaCircular;

public class NovaListaCircularOrdenada<X extends Comparable<X>> extends BaseListaCircular<X> {
       
    public NovaListaCircularOrdenada() {
        super();
    }       
   
    public void insercaoEmOrdem(X i) throws Exception{
        if(i == null)
            throw new Exception("Informação ausente");
        X inserir = null;

        if(i instanceof Cloneable)
            inserir = (X)super.meuCloneDeX(i);
        else    
            inserir = i;

        if(super.primeiro == null) {
            super.primeiro = new No(inserir);
            super.ultimo = super.primeiro;
            return;
        }
        else {
            No atual = super.primeiro;
            No atualProx = super.primeiro.getProx();
            int indiceMaior = 0;
            do {
                if(inserir.compareTo(atual.getInfo()) > 0 && inserir.compareTo(atualProx.getInfo()) < 0) {
                    atual.setProx(new No(inserir, atualProx));
                    break;
                }                
                atual = atual.getProx();
                atualProx = atualProx.getProx();
            } while(atual == super.ultimo);
            
            if(indiceMaior == 0) {
                super.primeiro = new No(inserir, super.primeiro);
                super.ultimo.setProx(super.primeiro);
                return;
            }
            if(indiceMaior == super.getQuantidade()) {
                super.ultimo.setProx(new No(inserir, super.primeiro));
                super.ultimo = super.ultimo.getProx();
                return;
            }
            
        }        
    }

    public NovaListaCircularOrdenada(NovaListaCircularOrdenada<X> modelo) throws Exception {
        if(modelo == null)
            throw new Exception("Modelo ausente");
        if(modelo.primeiro == null)
            return;
        super.primeiro = new No(modelo.primeiro.getInfo());

        No atualDoSuper = super.primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        for(;;) {
            atualDoSuper.setProx (new No (atualDoModelo.getInfo()));
            atualDoSuper   = atualDoSuper  .getProx ();
            atualDoModelo = atualDoModelo.getProx ();
            
            if(atualDoModelo == this.ultimo)
                break;
        }

        this.ultimo = atualDoSuper;
        this.ultimo.setProx(this.primeiro);
    }

    public Object clone() {
        NovaListaCircularOrdenada<X> ret = null;
        try {
            ret = new NovaListaCircularOrdenada<X>(this);
        } catch (Exception e) {}

        return ret;
    }
}
