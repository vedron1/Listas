package src;
import src.listas.ListaSimplesDesordenada;

public class QueueLista<X> implements Cloneable {
    ListaSimplesDesordenada<X> ls;

    public QueueLista(){
        ls= new ListaSimplesDesordenada<X>();
    }

    public void guardeUmItem(X x)throws Exception{
        ls.guardeUmItemNoFinal(x);
    }

    public X recupereUmItem()throws Exception{
        return ls.recupereItemDoInicio();
    }

    public X removaUmItem()throws Exception{
        X ret = this.recupereUmItem();
        ls.removaItemDoInicio();
        return ret;
    }

    @Override
    public String toString(){
        String ret ="";
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
        QueueLista<X> q = (QueueLista)obj;
        if(!this.ls.equals(q.ls)) return false;
        return true;
    }

    @Override
    public int hashCode(){
        int ret = 8;
        ret = 23*ret + this.ls.hashCode();
        if(ret<0) ret = -ret;
        return ret;
    }

    public QueueLista(QueueLista<X> model) throws Exception{
        if(model == null) throw new Exception("modelo ausente");
        this.ls = model.ls;        
    }

    @Override
    public Object clone(){
        QueueLista<X> ret = null;
        try {
            ret = new QueueLista<X>(this);
        } catch (Exception e) {}
        return ret;
    }
}
