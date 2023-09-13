package src.baseLista.desordenada;
import java.lang.reflect.*;
import src.baseLista.BaseListaCircular;
public class NovaListaCircularDesordenada<X> extends BaseListaCircular<X> {
       
    public NovaListaCircularDesordenada() {
        super();
    }       
   
    public void guardeUmItemNoInicio(X i) throws Exception {
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
        }
        else {
            super.primeiro = new No(inserir, super.primeiro);
            super.ultimo.setProx(super.primeiro);
        }
    }

    public void guardeUmItemNoFinal(X i) throws  Exception {
        if(i == null)
            throw new Exception("Informação ausente");
        X inserir = null;
        if(i instanceof Cloneable)
            inserir = (X)meuCloneDeX(i);
        else
            inserir = i;
        
        if(super.ultimo == null) {
            super.ultimo = new No(inserir);
            super.primeiro = super.ultimo;
        }
        else {
            super.ultimo.setProx(new No(inserir, super.primeiro));
            super.ultimo = super.ultimo.getProx();
            // nota: conferir se está certo
            //super.ultimo.setProx(super.primeiro);            
        }
    }

    public NovaListaCircularDesordenada(NovaListaCircularDesordenada<X> modelo) throws Exception {
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
        NovaListaCircularDesordenada<X> ret = null;
        try {
            ret = new NovaListaCircularDesordenada<X>(this);
        } catch (Exception e) {}

        return ret;
    }
}
