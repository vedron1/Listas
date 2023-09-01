package src.listas;

import java.lang.reflect.*;

public class ListaSimplesDesordenada <X> {
    
    private class No {
    
        private X  info;
        private No prox;

        public No (X i, No p) {
            this.info = i;
            this.prox = p;        
        }

        public No (X i) {
            this.info = i;
            this.prox = null;
        }

        public X getInfo () {
            return this.info;
        }

        public No getProx () {
            return this.prox;
        }

        public void setInfo (X i) {
            this.info = i;
        }

        public void setProx (No p) {
            this.prox = p;
        }
    }

    private No primeiro, ultimo;

    public ListaSimplesDesordenada () {
        this.primeiro=this.ultimo=null;
    }

    private X meuCloneDeX (X x) {
        
        X ret=null;

        try {
            Class<?> classe         = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo           = classe.getMethod("clone",tipoDosParms);
            Object[] parms          = null;
            ret                     = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro) {}
        catch(IllegalAccessException erro) {}
        catch(InvocationTargetException erro) {}

        return ret;
    }

    public void guardeUmItemNoInicio (X i) throws Exception {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X inserir=null;
        if (i instanceof Cloneable)
            inserir = (X)meuCloneDeX(i);
        else
            inserir = i;
            
        this.primeiro = new No (inserir,this.primeiro);

        if (this.ultimo==null)
            this.ultimo=this.primeiro;
    }

    public void guardeUmItemNoFinal (X i) throws Exception {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X inserir=null;
        if (i instanceof Cloneable)
            inserir = (X)meuCloneDeX(i);
        else
            inserir = i;
            
        if (this.ultimo==null) {
            this.ultimo   = new No (inserir);
            this.primeiro = this.ultimo;
        }
        else {
            this.ultimo.setProx (new No (inserir));
            this.ultimo = this.ultimo.getProx();
        }
    }

    public X getElemento(int i) throws Exception {
        if(i <= 0 || i > this.getQuantidade()) throw new Exception("índice inválido");
        No ret = this.primeiro;       
        for(int a = 1; a<i; a++){
            ret = ret.getProx();
        }
        return ret.getInfo();
    }

    public X getIezimo(int i) throws Exception {
        if(i < 0) throw new Exception("indice invalido");
        int posicao = 1;
        No atual = this.primeiro;
        for(;;) {
            if(atual == null) throw new Exception("indice invalido");
            if(posicao == i) return atual.getInfo();
            posicao++;
            atual = atual.getProx();
        }
    }
    
    //1- Contar quantas vezes o elemento dado existe na lista.
    public int getQtdElemX(X x) throws Exception {
        if(x == null) throw new Exception("elemento nulo");
        No atual = this.primeiro;
        int qtd = 0;
        for(;;) {
            if(atual == null) return qtd;
            if(atual.getInfo() == x) qtd++;
            atual = atual.getProx();            
        }
    }

    //2- Obter o tamanho da lista
    public int getQtdLista() {
        int qtd = 0;
        No atual = this.primeiro;
        for(;;) {
            //verificar se existe um próximo Nó
            if(atual.getProx() == null) return qtd;
            qtd++;
            atual = atual.getProx();
        }      
    }

    //3- Inverter a lista criando outra e retornando
    public ListaSimplesDesordenada<X> listaNovaInvertida() throws Exception {
        ListaSimplesDesordenada<X> invertida = new ListaSimplesDesordenada<X>();
        No atual = this.primeiro;
        int cont = 0;
        while(cont <= this.getQtdLista()) {            
            invertida.guardeUmItemNoInicio(atual.getInfo());
            atual = atual.getProx();
            cont++;
        }
        return invertida;
    }

    // 4- Inverter a lista sem criar outra
    public ListaSimplesDesordenada<X> listaInvertida() throws Exception {
        ListaSimplesDesordenada<X> invertida = new ListaSimplesDesordenada<X>();
        int cont = 0;
        No atual = this.primeiro;
        while(cont <= this.getQtdLista()) {
            invertida.guardeUmItemNoInicio(atual.getInfo());
            atual = atual.getProx();
            this.guardeUmItemNoFinal(invertida.primeiro.getInfo());
            cont++;
       }        
       return this;
    }

    public X recupereItemDoInicio () throws Exception {
        if (this.primeiro==null)
            throw new Exception ("Nada a obter");

        X ret = this.primeiro.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    public X recupereItemDoFinal () throws Exception {
        if (this.primeiro==null)
            throw new Exception ("Nada a obter");

        X ret = this.ultimo.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
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
    }
    
    public void removaItemDoFinal () throws Exception {
        if (this.primeiro==null)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) {
            this.primeiro=this.ultimo=null;
            return;
        }

        No atual;
        for (atual=this.primeiro;
             atual.getProx()!=this.ultimo;
             atual=atual.getProx())

        atual.setProx(null);
        this .ultimo=atual;
    }
    
    public int getQuantidade () {
        No  atual=this.primeiro;
        int ret  =0;

        while (atual!=null) {
            ret++;                
            atual = atual.getProx();
        }        
        return ret;
    }

    public boolean tem (X i) throws Exception {
        if (i==null)
            throw new Exception ("Informacao ausente");
		
        No atual=this.primeiro;

        while (atual!=null) {
            if (i.equals(atual.getInfo()))
                return true;
                
            atual = atual.getProx();
        }
        
        return false;
	}
	
	public void removaItemIndicado (X i) throws Exception
	{
        if (i==null)
            throw new Exception ("Informacao ausente");

        boolean removeu=false;

        for(;;) {
            if (this.primeiro==null/*&&this.ultimo==null*/)
                break;

            if (!i.equals(this.primeiro.getInfo()))
                break;
                
            if (this.ultimo==this.primeiro)
                this.ultimo=null;

            this.primeiro=this.primeiro.getProx();

            removeu=true;
        }

        if (this.primeiro!=null) {
            No atual=this.primeiro;

            forever:for(;;) {
                if (atual.getProx()==null)
                    break;

                while (i.equals(atual.getProx().getInfo())) {
                    if (this.ultimo==atual.getProx())
                        this.ultimo=atual;

                    atual.setProx(atual.getProx().getProx());

                    removeu=true;

                    if (atual==this.ultimo)
                        break forever;
                }

                atual=atual.getProx();
            }
        }

        if (!removeu)
            throw new Exception ("Informacao inexistente");
	}

    public boolean isVazia () {
        return this.primeiro==null/*&&this.ultimo==null*/;
    }
    
    public String toString () {
        String ret="[";

        No atual=this.primeiro;

        while (atual!=null) {
            ret=ret+atual.getInfo();

            if (atual!=this.ultimo)
                ret=ret+",";

            atual=atual.getProx();
        }

        return ret+"]";
    }

    public boolean equals (Object obj) {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        ListaSimplesDesordenada<X> lista =
       (ListaSimplesDesordenada<X>)obj;

        No atualThis =this .primeiro;
        No atualLista=lista.primeiro;

        while (atualThis!=null && atualLista!=null) {
            if (!atualThis.getInfo().equals(atualLista.getInfo()))
                return false;

            atualThis  = atualThis .getProx();
            atualLista = atualLista.getProx();
        }

        if (atualThis != null)
            return false;

        if (atualLista != null)
            return false;
            
        return true;
    }
    
    public int hashCode () {
        final int PRIMO = 13;
        
        int ret = 666; 

        for (No atual=this.primeiro;
             atual!=null;
             atual=atual.getProx())
             ret = PRIMO*ret + atual.getInfo().hashCode();

        if (ret<0) ret = -ret;

        return ret;
    }
    
    // construtor de copia
    public ListaSimplesDesordenada (ListaSimplesDesordenada<X> modelo) throws Exception {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        if (modelo.primeiro==null)
            return; 

        this.primeiro = new No (modelo.primeiro.getInfo());

        No atualDoThis   = this  .primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        while (atualDoModelo!=null) {
            atualDoThis.setProx (new No (atualDoModelo.getInfo()));
            atualDoThis   = atualDoThis  .getProx ();
            atualDoModelo = atualDoModelo.getProx ();
        }

        this.ultimo = atualDoThis;
    }

    public Object clone () {
        ListaSimplesDesordenada<X> ret=null;

        try {
            ret = new ListaSimplesDesordenada (this);
        }
        catch (Exception erro) {} 

        return ret;
    }
}
