package src;

import src.listas.ListaDuplamenteLigadaCircular;
import src.baseLista.ordenada.*;

public class Runner {

    public static void run() {

        try {
            NovaListaCircularOrdenada<Integer> nlco = new NovaListaCircularOrdenada<Integer>();
            nlco.insercaoEmOrdem(1);
            nlco.insercaoEmOrdem(2100);
            nlco.insercaoEmOrdem(23);
            nlco.insercaoEmOrdem(2);
            nlco.insercaoEmOrdem(402);
            nlco.insercaoEmOrdem(2);
            System.out.println(nlco);


            // ListaDuplamenteLigadaCircular<String> ldlc = new ListaDuplamenteLigadaCircular<String>();
            // ldlc.guardeUmItemNoInicio("a");
            // ldlc.guardeUmItemNoInicio("b");
            // ldlc.guardeUmItemNoInicio("c");
            // ldlc.guardeUmItemNoFinal("d");
            // ldlc.guardeUmItemNoFinal("e");
            // ldlc.guardeUmItemNoFinal("f");
            // ldlc.guardeUmItemNoFinal("g");
            // ldlc.guardeUmItemNoFinal("h");
            // System.out.println(ldlc);
            // System.out.println(ldlc.getIezimo(-1));
            // ListaDuplamenteLigada<String> ldl = new ListaDuplamenteLigada();
            // System.out.println(ldl.getQuantidade());
            // ldl.guardeUmItemNoFinal("d");
            // ldl.guardeUmItemNoFinal("a");
            // ldl.guardeUmItemNoFinal("b");
            // ldl.guardeUmItemNoFinal("c");
            // ldl.guardeUmItemNoFinal("d");
            // ldl.guardeUmItemNoFinal("e");
            // ldl.guardeUmItemNoFinal("f");
            // System.out.println(ldl.getQuantidade());
            // System.out.println(ldl.getIezimo(7));
            // System.out.println(ldl.toString());
            // System.out.println(ldl.recupereItemDoInicio());
            // ListaCircular<String> lc = new ListaCircular<String>();
            // lc.guardeUmItemNoInicio("a");
            // lc.guardeUmItemNoInicio("b");
            // lc.guardeUmItemNoInicio("c");
            // lc.guardeUmItemNoFinal("x");
            // lc.guardeUmItemNoFinal("h");
            // lc.guardeUmItemNoFinal("p");
            // System.out.println(lc);
            // System.out.println(lc);
            // System.out.println(lc);
            // System.out.println(lc.getQuantidade());
            // System.out.println(lc.getElemento(3));
            // System.out.println(lc);
            // lc.inverterListaCircular();
            // System.out.println(lc);
            // ListaSimplesDesordenada<String> ls = new ListaSimplesDesordenada<String>();
            // ls.guardeUmItemNoInicio("Kotlin");
            // ls.guardeUmItemNoFinal("JAVA");
            // ls.guardeUmItemNoFinal("C++");
            // ls.guardeUmItemNoFinal("JAVA");
            // ls.guardeUmItemNoFinal("Pasqual");
            // System.out.println(ls);
            //System.out.println(ls.listaNovaINvertida());
            // System.out.println(ls.listaInvertida());
            
            // StackListaSimplesDesordenada<String> sls = new StackListaSimplesDesordenada<String>();
            // sls.guardeUmItem("Json");
            // sls.guardeUmItem("JavaScript");
            // sls.guardeUmItem("SqlServer");
            
            // System.out.println(sls.recupereUmItem());
            // System.out.println(sls.removaUmItem());
            // System.out.println(sls.recupereUmItem());
            // System.out.println(sls);

            // QueueLista<String> qls = new QueueLista<String>();
            // qls.guardeUmItem("João");
            // qls.guardeUmItem("Vedroni");
            // qls.guardeUmItem("Guanliel");
            // System.out.println(qls.recupereUmItem());
            // System.out.println(qls.removaUmItem());
            // System.out.println(qls.recupereUmItem());
            // System.out.println(qls);
            // System.out.println(qls.removaUmItem());
            // System.out.println(qls.removaUmItem());
            // System.out.println(qls.removaUmItem());

        } catch (Exception e) {}
    }
}
