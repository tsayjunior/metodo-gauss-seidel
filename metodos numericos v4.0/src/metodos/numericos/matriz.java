/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos.numericos;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Pc
 */
public class matriz {
    double [][]tam;
    int nFila;
    int nCol;
    int f;
    int c;
    double aux;
    double [][]x;
    int xfil;
    double tol;

    public matriz() {
        tam = new double [0][0];
        this.nFila = 0;
        this.nCol=0;
        f=0;
        c=-1;
        aux=0;
       xfil=3;
        x=new double[0][0];
        tol=0.0001;
        
    }

    public double getTol() {
        return tol;
    }

    public void setTol(double tol) {
        this.tol = tol;
    }

    public double[][] getTam() {
        return tam;
    }

    public void setTam(int t) {
        if (t>0) {
            tam = new double [t][t+1];
        nFila=t;
        nCol=t+1;
        x=new double[3][t];
        xfil=3;
        }
        
    }
    public void inicializar(){
        Random r= new Random();
        for (int i = 0; i < nFila; i++) {
            for (int j = 0; j < nCol; j++) {
                tam[i][j]=r.nextInt(100);
            }
        }
    }

    @Override
    public String toString() {
        String s="";
        for (int i = 0; i < nFila; i++) {
            for (int j = 0; j < nCol; j++) {
                if (j==nCol-1) {
                    s+="= "+tam[i][j];
                }else{
                s+=+tam[i][j]+"x"+(j+1)+"\t";
                }
            }
            s+="\n";
        }
        return s;
    }
    public void avanzar(){
        /*if (f>=nFila) {
              JOptionPane.showMessageDialog(null,"este es el ultimo digito de la matriz");
              aux=1;
        }*/
        if (c>=nCol-1) {
              f++;
              c=0;
                if (f>=nFila) {
                    JOptionPane.showMessageDialog(null,"este es el ultimo digito de la matriz");
                    f--;
                    c=nCol-1;
                    aux=1;
                }
        }else{
                c++;
        }
    }
    public void retroceder(){
        /*if (f<=-1) {
            JOptionPane.showMessageDialog(null, "este es el primer digito de la matriz");
        */if (c==0) {
            c=nCol-1;
            f--;
            if (f<=-1) {
            JOptionPane.showMessageDialog(null,"este es el primer digito de la matriz");
            f++;
            c=-1;
        }
        }else{
            c--;
            aux=0;
        }
    }
    public void grabar(double a){
        if (aux==0) {
            tam[f][c]=a;
        }
        //if (f==(nFila-1)&&c==(nCol-1)) {
        //    tam=tam;
        //}else{
           // tam[f][c]=a;
        //}
    }
    public void principio(){
            f=0;
            c=-1;
            aux=0;
    }
    public double mayFil(int f){
        double aux=Math.abs(tam[f][0]);
        for (int i = 0; i < nCol-2; i++) {
                if (aux<Math.abs(tam[f][i+1])) {
                aux=Math.abs(tam[f][i+1]);
                
            }
        }
        return aux;
    }
    public double mayCol(int f){
        double aux=Math.abs(tam[0][f]);
        for (int i = 0; i < nFila-1; i++) {
                if (aux<Math.abs(tam[i+1][f])) {
                aux=Math.abs(tam[i+1][f]);
            }
        }
        return aux;
    }
    public int posMay(int f){
        int s=0;
        
        for (int i = 0; Math.abs(tam[f][i])!=mayFil(f) ; i++) {
            s=i+1;
        }
    return s;   
    }
    public void MatrizOrdDia(){
        double v[][]=new double[nFila][nCol];
        for (int i = 0; i < nFila; i++) {
            if (Math.abs(tam[i][i])!=mayFil(i)) {
                for (int j = 0; j < nCol; j++) {
                    int aux=posMay(i);
                     v[aux][j]=tam[i][j];
        }
            }else{
                for (int j = 0; j < nCol; j++) {
                v[i][j]=tam[i][j];
                }
            }
          }
        tam=v;
    }
    public boolean maySeRepita(){
        boolean aux2=false;
            for (int j = 0; j < nFila; j++) {
                  int c=0;
                double aux=mayFil(j);
            for (int i = 0; i < nCol-2; i++) {
                if (aux==tam[j][i]) {
                        c++;
                    if (c>1) {
                        JOptionPane.showMessageDialog(null, "el mayor se repite en una fila");
                        aux2= true;
                    }
                 }
            }
            }
            return aux2;
    }
    public boolean dospPosMayCol(){
        int c=0;
        int aux=0;
            for (int j = 0; j < nFila; j++) {
                aux=aux+posMay(j);
                c=c+j;
                } 
        if (aux!=c) {
                    JOptionPane.showMessageDialog(null, "hay dos o mas mayores en una columna");
                    return true;
                    }   
              
        return false;
    }
    public void diagonalDominante(){
            if (dospPosMayCol()||maySeRepita()) {
                tam=tam;
            }else{
                MatrizOrdDia();
            }
}    
    
    public void tolerancia(int a){
        int c=0;
        for (int i = 0; i < nCol-1; i++) {
                double aux=Math.abs(x[a][i]-x[a-1][i]);
                //Math.abs(aux);
                if (aux<tol) {
                c++;
            }
            }
        if (c!=nCol-1) {
            xfil=xfil+1;
        }
    }
   public void ecuacion(int a){
       double s=0;
       //x[1][0]=0;
        for (int i = 0; i <nFila; i++) {
            s=0;
            //int aux=posMay(i);
            for (int j = 0; j < nCol-1; j++){ 
                 if (tam[i][j]==mayFil(i)) {
                     s=s+tam[i][nCol-1];
                }else{
                    if (x[a][j]!=0) {
                        s=s+x[a][j]*tam[i][j];
                    }else{
                         s=s+tam[i][j]*x[a-1][i];                    
                    }
            }
            }
            s=s*(1/mayFil(i));
            x[a][i]=s;
        }
    }
   /*public void llenarX(){
       
   }*/
   public void gaussSeidel(){
       
       for (int i = 0; i < xfil; i++) {
           if (i<2) {
           for (int j = 0; j < nCol-1; i++) {
                   x[i][j]=0;
           }
       }else{
               ecuacion(i);
               tolerancia(i);
               x=new double[xfil][nCol-1];
           }
       }
   }
   public void gaussSeidelV2(){
       x=new double[xfil][nCol-1];
       for (int i = 0; i < xfil; i++) {
           if (i<2) {
           for (int j = 0; j < nCol-1; i++) {
                   x[i][j]=0;
           }
       }else{
               double s=0;
               for (int j = 0; j < nFila-1; j++) {
                   s=0;
                   for (int k = 0; k < nCol-1; k++) {
                       if (k==j) {
                           s=s+tam[j][nCol-1];
                       }else{
                           if (x[i][j]>0) {
                               s=s+x[i][j]*tam[j][k];
                           }else{
                               s=s+x[i-1][j]*tam[j][k];
                           }
                       }
                       s=s*(1/(tam[j][j]));
                       x[i][j]=s;
                   }
               }
              //tolerancia(i);
              int c=0;
               for (int j = 0; j < nCol-1; j++) {
                   double aux=x[i][j]-x[i-1][j];
                   Math.abs(aux);
                   if (aux<tol) {
                       c++;
                   }
                   
               }if (c!=nCol-1) {
                       xfil=xfil+1;
                   }
               x=new double[xfil][nCol-1];
            }
       }
   }
   ////////////////////////////////////////////////////////////////////
   public void llenarCon0(){
      // xfil=3;
      // x=new double[xfil][nCol-1];
       for (int i = 0; i < xfil; i++) {
           for (int j = 0; j < nCol-1; j++) {
               x[i][j]=0;
           }
       }
   }
   public void llenarUltFilX(){
      // for (int j = 0; j < nCol-1; j++) {
           
       //x[1][0]=0;
        for (int i = 0; i <nFila; i++) {
            double s=0;
            //int aux=posMay(i);
            for (int k = 0; k < nCol-1; k++){ 
                 if (k==i) {
                     s=s+tam[i][nCol-1];
                }else{
                    if (x[xfil-1][k]!=0) {
                        s=s+x[xfil-1][k]*(tam[i][k]*-1);
                    }else{
                        s=s+(tam[i][k]*-1)*x[xfil-2][k];                    
                    }
            }
            }
            s=s*(1/tam[i][i]);
            x[xfil-1][i]=s;
        }

       //}
   }
   public String mostrarX(){
        String m="";
        for (int i = 0; i < xfil; i++) {
            for (int j = 0; j < nCol-1; j++) {
                if (i==0) {
                    m=m+ "X"+(j+1)+"\t"+"\t";
                }/*if (i==1) {
                    m=m+"0"+"\t";
                }*/
                else{
                    m=m+x[i][j]+"\t";
                }
            }
            m=m+"\n";
        }
        return m;
    }
   public void gaussSeidelV3(){
       llenarCon0();
       //x=new double[xfil][nCol-1];
       for (int i = 2; i < xfil; i++) {
       x=new double[xfil][nCol-1];
           llenarUltFilX();
           //tolerancia(xfil-1); 
       }
   }
   public void gaussSeidelV4(){
       xfil=3;
       x=new double[xfil][nCol-1];
       llenarCon0();
       double [][]s=new double[xfil][nCol-1];
       for (int i = 2; i < xfil; i++) {
           llenarUltFilX();
           tolerancia(xfil-1);
           if (i+1<xfil) {
               s=new double[xfil][nCol-1];
               for (int j = 0; j < xfil; j++) {
                   for (int k = 0; k < nCol-1; k++) {
                       if (j==xfil-1) {
                           s[j][k]=0;
                       }else{
                           s[j][k]=x[j][k];
                       }
                   }
               }
           }
           x=s;
       }
   }
   public String iteraciones(){
       
       String s=String.valueOf(xfil-1);
       return s;
   }
}
