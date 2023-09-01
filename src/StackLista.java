package src;

import src.listas.ListaSimplesDesordenada;

public class StackLista<X> implements Cloneable {
    private ListaSimplesDesordenada<X> ls;

    public StackLista(){
        ls = new ListaSimplesDesordenada<X>();
    }

    public void guardeUmItem(X x)throws Exception{        
        ls.guardeUmItemNoFinal(x);       
    }

    public X recupereUmItem()throws Exception{
        return ls.recupereItemDoFinal();
    }

    public X removaUmItem()throws Exception{
        X ret = this.recupereUmItem();
        ls.removaItemDoFinal();
        return ret;
    }

    @Override
    public String toString(){
        String ret = "";
        try {
            ret = ""+this.recupereUmItem();            
        } catch (Exception e) {}        
        return ret;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        StackLista<X> s = (StackLista)obj;
        if(!this.ls.equals(s.ls)) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 8;
        ret = 23*ret + this.ls.hashCode();
        if(ret<0) ret = -ret;
        return ret;
    }

    public StackLista(StackLista<X> model) throws Exception{
        if(model == null) throw new Exception("Modelo ausente");
        this.ls = model.ls;
    }

    @Override
    public Object clone(){
        StackLista<X> ret = null;
        try{
            ret = new StackLista<X>(this);
        }catch(Exception e){}
        return ret;
    }
}
