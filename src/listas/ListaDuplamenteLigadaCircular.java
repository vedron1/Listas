package src.listas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ListaDuplamenteLigadaCircular<X> {
    
    private class No {
    
        X info;
        No prox, ant;

        public No(X i, No a, No p) {
            this.info = i;
            this.ant = a;
            this.prox = p;
        }

        public No(X i) {
            this.info = i;
            this.ant = null;
            this.prox = null;
        }

        public X getInfo() {
            return this.info;
        }

        public No getAnt() {
            return this.ant;
        }

        public No getProx() {
            return this.prox;
        }

        public void setInfo(X i) {
            this.info = i;
        }

        public void setAnt(No a) {
            this.ant = a;
        }

        public void setProx(No p) {
            this.prox = p;
        }
    }

    public No primeiro, ultimo;

    public ListaDuplamenteLigadaCircular() {
        this.primeiro=this.ultimo=null;        
    }

    private X meuCloneDex(X x) {
        X ret=null;
        try {
            Class<?> classe = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo = classe.getMethod("clone", tipoDosParms);
            Object [] parms = null;
            ret = (X)metodo.invoke(x, parms);
        } 
        catch (NoSuchMethodException e){}
        catch(IllegalAccessException e){}
        catch(InvocationTargetException e){}

        return ret;
    }

    public void guardeUmItemNoInicio (X i) throws Exception {
        if(i == null) 
            throw new Exception("Informacao ausente");
        X inserir = null;
        if(i instanceof Cloneable) 
            inserir = (X)meuCloneDex(i);
        else 
            inserir = i;
        if(this.primeiro == null) {
            this.primeiro = new No(inserir);
            this.ultimo = this.primeiro;
        }
        else {
            this.primeiro = new No(inserir, this.ultimo, this.primeiro);
            this.primeiro.getProx().setAnt(this.primeiro);
        }
    }

    public void guardeUmItemNoFinal (X i) throws Exception {
        if(i == null)
            throw new Exception ("Informacao ausente");
        
        X inserir=null;
        if(i instanceof Cloneable)
            inserir = (X)meuCloneDex(i);
        else
            inserir = i;

        if(this.ultimo == null) {
            this.ultimo = new No(inserir);
            this.primeiro = this.ultimo;
        }
        else {
            this.ultimo.setProx(new No(inserir));
            this.ultimo.getProx().setAnt(this.ultimo);
            this.ultimo = this.ultimo.getProx();
            this.ultimo.setProx(this.primeiro);
        }
    }

    public X getIezimo(int i) throws Exception {
        if(i < 0) throw new Exception("indice invalido");
        int posicao = 1;
        No atual = this.primeiro;
        for(;;) {
            if(posicao == i) return atual.getInfo();
            posicao++;
            if(atual == this.ultimo) throw new Exception("indice invalido");
            atual = atual.getProx();
        }
    }
    
    public X recupereItemDoInicio () throws Exception
    {
        if (this.primeiro==null)
            throw new Exception ("Nada a obter");

        X ret = this.primeiro.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDex (ret);
            
        return ret;
    }

    public X recupereItemDoFinal () throws Exception
    {
        if (this.primeiro==null/*&&this.ultimo==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.ultimo.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDex (ret);
            
        return ret;
    }

    public void removaItemDoInicio () throws Exception {
        if (this.primeiro==null)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) {
            this.primeiro=this.ultimo=null;
            return;
        }

        this.primeiro = this.primeiro.getProx();
        this.primeiro.setAnt(this.ultimo);
    }
    
    public void removaItemDoFinal () throws Exception {
        if (this.primeiro==null)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) {
            this.primeiro=this.ultimo=null;
            return;
        }
        
        this.ultimo = this.ultimo.getAnt();
        this.ultimo.setProx(this.primeiro);
    }
    
    public int getQuantidade ()
    {
        No  atual=this.primeiro;
        int ret  =0;

        for(;;) {
            ret ++;
            if(atual == this.ultimo)
                return ret;
            atual = atual.getProx();
        }
    }

    public boolean tem (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");
		
        No atual=this.primeiro;

        // while (atual!=null)
        // {
        //     if (i.equals(atual.getInfo()))
        //         return true;
                
        //     atual = atual.getProx();
        // }
        for(;;) {
            if(i.equals(atual.getInfo()))
                return true;
            if(atual == this.ultimo)
                return false;
            atual = atual.getProx();
        }
	}
	
	public void removaItemIndicado (X i) throws Exception
	{
        if (i==null)
            throw new Exception ("Informacao ausente");

        boolean removeu=false;

        for(;;) // FOR EVER (repete até break)
        {
            if (this.primeiro==null/*&&this.ultimo==null*/)
                break;

            if (!i.equals(this.primeiro.getInfo())) {
                break;
            }
                
            if (this.ultimo==this.primeiro)
                this.ultimo=null;

            this.primeiro=this.primeiro.getProx();
            this.primeiro.setAnt(this.ultimo);

            removeu=true;
        }
            
        if (this.primeiro!=null/*&&this.ultimo!=null*/)
        {
            No atual=this.primeiro;
            forever:for(;;) // repete ate break
            {
                if (atual.getProx()==null)
                    break;

                while (i.equals(atual.getProx().getInfo()))
                {
                    if (this.ultimo==atual.getProx())
                        this.ultimo=atual;

                    atual.setProx(atual.getProx().getProx());                       

                    removeu=true;

                    if (atual!=this.ultimo)
                        atual.getProx().setAnt(atual);
                    else
                        break forever;
 
                }

                atual=atual.getProx();
            }
        }

        if (!removeu)
            throw new Exception ("Informacao inexistente");
	}

    public boolean isVazia ()
    {
        return this.primeiro==null/*&&this.ultimo==null*/;
    }
    
    public String toString ()
    {
        String ret="[";

        No atual=this.primeiro;

        for(;;) {
            ret = ret+atual.getInfo();
            if(atual == this.ultimo)
                return ret+"]";
            else
                ret  = ret + ",";
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

        ListaDuplamenteLigadaCircular<X> lista =
        (ListaDuplamenteLigadaCircular<X>)obj;

        No atualThis =this .primeiro;
        No atualLista=lista.primeiro;

        while (atualThis!=this.ultimo && atualLista!=lista.ultimo)
        {
            if (!atualThis.getInfo().equals(atualLista.getInfo()))
                return false;

            atualThis  = atualThis .getProx();
            atualLista = atualLista.getProx();
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

        for (No atual=this.primeiro;
             atual!=null;
             atual=atual.getProx())
             ret = PRIMO*ret + atual.getInfo().hashCode();

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
    public ListaDuplamenteLigadaCircular (ListaDuplamenteLigadaCircular<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        if (modelo.primeiro==null)
            return; // sai do construtor, pq this.primeiro ja é null

        this.primeiro = new No (modelo.primeiro.getInfo());

        No atualDoThis   = this  .primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        while (atualDoModelo!=modelo.ultimo)
        {
            atualDoThis.setProx (new No (atualDoModelo.getInfo(),atualDoThis, null));
            atualDoThis   = atualDoThis  .getProx ();
            atualDoModelo = atualDoModelo.getProx ();
        }

        this.ultimo = atualDoThis;
    }

    public Object clone ()
    {
        ListaDuplamenteLigadaCircular<X> ret=null;

        try
        {
            ret = new ListaDuplamenteLigadaCircular (this);
        }
        catch (Exception erro)
        {} // sei que this NUNCA é null e o contrutor de copia da erro quando seu parametro é null

        return ret;
    }
}
