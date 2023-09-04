package src.baseLista;
import java.lang.reflect.*;
public class BaseListaCircular<X> {
    protected class No {
        private X info;
        private No prox;

        public No(X i, No p) {
            this.info = i;
            this.prox = p;
        }

        public No(X i) {
            this.info = i;
            this.prox = null;
        }
       
        public X getInfo() {
            return this.info;
        }

        public No getProx(){
            return this.prox;
        }

        public void setInfo(X i){
            this.info = i;
        }

        public void setProx(No p){
            this.prox = p;
        }
    }

    protected No primeiro, ultimo;

    public BaseListaCircular(){
        this.primeiro = this.ultimo = null;
    }

    protected X meuCloneDeX (X x){
        X ret = null;

        try{
            Class<?> classe = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo = classe.getMethod("clone", tipoDosParms);
            Object[] parms = null;
            ret = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro){}
        catch(IllegalAccessException erro){}
        catch(InvocationTargetException erro){}

        return ret;
    }   

    public void removaItemDoInicio () throws Exception {
        if (this.primeiro==null /*&& this.ultimo==null*/)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) //so 1 elemento
        {
            this.primeiro=this.ultimo=null;
            return;
        }

        this.primeiro = this.primeiro.getProx();
        this.ultimo.setProx(this.primeiro);
    }

    public void removaItemDoFinal () throws Exception
    {
        if (this.primeiro==null/*&&this.ultimo==null*/)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) //so 1 elemento
        {
            this.primeiro=this.ultimo=null;
            return;
        }

        No atual;
        for (atual=this.primeiro;
             atual.getProx()!=this.ultimo;
             atual=atual.getProx())
             /*comando vazio*/;

        atual.setProx(null);
        this .ultimo=atual;
        this.ultimo.setProx(this.primeiro);
    }

    public int getQuantidade() {
        int qtd = 0;
        No atual = this.primeiro;
        
        if(this.primeiro == null)
            return 0;
        
        for(;;) {
            qtd++;
            if(atual == this.ultimo)
                return qtd;
            atual = atual.getProx();
        }
    }

    public X getElemento(int indice) throws Exception {
        if (indice < 1 || indice > this.getQuantidade())
            throw new Exception("Indice inválido");
        No atual = this.primeiro;
        int cont = 1;
        for(;;) {
            if(cont == indice)
                return atual.getInfo();
            atual = atual.getProx();
            cont++;
        }
    }

    public X recupereItemDoInicio () throws Exception{
        if (this.primeiro==null/*&&this.fim==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.primeiro.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    public X recupereItemDoFinal () throws Exception
    {
        if (this.primeiro==null/*&&this.ultimo==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.ultimo.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    public boolean isVazia () {
        return this.primeiro==null/*&&this.ultimo==null*/;
    }

    public boolean tem (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");
		
        No atual=this.primeiro;

       for(;;) {
            if (i.equals(atual.getInfo()))
                return true;
            if(atual == this.ultimo)
                return false;
                
            atual = atual.getProx();
        }        
	}
        
    public String toString() {
        String ret = "[";        
        No atual = this.primeiro;
        for(;;) {
            ret = ret + atual.getInfo();
            if(atual != ultimo)
                ret = ret + ",";
            else
                return ret+"]";
            atual = atual.getProx();
        }         
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        BaseListaCircular<X> lista = (BaseListaCircular<X>)obj;

        No atualThis =this .primeiro;
        No atualLista=lista.primeiro;

        while (atualThis!=this.ultimo && atualLista!=this.ultimo)
        {
            if (!atualThis.getInfo().equals(atualLista.getInfo()))
                return false;

            atualThis  = atualThis .getProx();
            atualLista = atualLista.getProx();
        }

        for(;;) {
            
            if(atualThis == this.ultimo && atualLista == lista.ultimo)
                break;

        }

        if (atualThis!=null  /* && atualLista==null */)
            return false;

        if (atualLista!=null /* && atualThis ==null */)
            return false;

        // atualThis==null && atualLista==null
        return true;
    }
    
    public int hashCode ()
    {
        final int PRIMO = 13; // qualquer número primo serve
        
        int ret=666; // qualquer inteiro positivo serve
        No atual = this.primeiro;
        
        for(;;) {
            ret = PRIMO*ret + atual.getInfo().hashCode();
            if(atual == this.ultimo)
                break;
            atual = atual.getProx();
        }
        
        if (ret<0) ret = -ret;

        return ret;
    }
    
    // construtor de copia
    public BaseListaCircular (BaseListaCircular<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        if (modelo.primeiro==null)
            return; // sai do construtor, pq this.primeiro ja é null

        this.primeiro = new No (modelo.primeiro.getInfo());

        No atualDoThis   = this  .primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        for(;;) {
            atualDoThis.setProx (new No (atualDoModelo.getInfo()));
            atualDoThis   = atualDoThis  .getProx ();
            atualDoModelo = atualDoModelo.getProx ();
            
            if(atualDoModelo == this.ultimo)
                break;
        }

        this.ultimo = atualDoThis;
        this.ultimo.setProx(this.primeiro);
        
    }

    public Object clone ()
    {
        BaseListaCircular<X> ret=null;

        try
        {
            ret = new BaseListaCircular<X> (this);
        }
        catch (Exception erro)
        {} // sei que this NUNCA é null e o contrutor de copia da erro quando seu parametro é null

        return ret;
    }
}
