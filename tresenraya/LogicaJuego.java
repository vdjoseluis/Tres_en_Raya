package tresenraya;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;

public class LogicaJuego {
    int turno, pX, pO; // Turno del jugador
    boolean habilitado; // Habilita y deshabilita el tablero

    /**
     * Inicializaremos el juego con las siguientes variables_
     * @param turno (Nos indicará el turno del jugador: 0 - X, 1 - O)
     * @param pX (Contiene el valor total de las victorias de este jugador)
     * @param pO (Contiene el valor total de las victorias de este jugador)
     */
    public LogicaJuego(int turno, int pX, int pO) {
        this.turno = turno;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Obtener turno
     * @return 
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Insertar turno
     * @param turno 
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }
    
    /**
     * Llamaremos a este método para cambiar de turno
     */
    public void cambioTurno(){
        // Inserta código aquí...
        turno= (getTurno()==1)? 0 : 1;
    }
    
    /**
     * Comprobar si se ha conseguido un tres en raya, 
     * en caso que se haya conseguido devolverá 1, en caso contrario retorna 0 y continúa el juego
     * @param matriz (Tablero de juego)
     * @return 
     */
    public int comprobarJuego(int matriz[][]){
    /* se tendrá que comprobar que las posiciones correspondientes tengan el 
       mismo valor; es decir se tiene que ir comparando las casillas entre sí; 
       de forma horizontal, vertical y diagonal, obligatoriamente.  . 
       Se evalúa utilizar las estructuras correctas, para reducir el número de 
       iteraciones. Todo de forma organizada y optimizada recordemos 
       que estamos en un módulo del tercer semestre del ciclo.  Pero, 
       por si acaso posible pseudocódigo:   
        Comprobar si existe tres en raya:
            Comprobar horizontal			
            si no, Comprobar vertical        
            si no, Comprobar en diagonal
        Si no hay tres en raya:
    */   
         // Inserta código aquí...
                
        for (int i = 0; i < 3; i++) {
            // Compruebo en horizontal
            if (matriz[i][0]== matriz[i][1])
                if (matriz[i][1]== matriz[i][2])return 1;
            // Compruebo en vertical
            if (matriz[0][i]== matriz[1][i])
                if (matriz[1][i]== matriz [2][i]) return 1;
        }
        //Comprobamos las diagonales

        if (matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2]) return 1;        
        if (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0]) return 1;
        
        return 0;
    }
    
     /**
     * En caso de Ganador, mostrará la ventana, dentro del tablero; con el 
     * mensaje de enhorabuena al gandador; y le preguntará si desea continuar 
     * jugando.
     * Se valorará el código limpio, comprensible, organizado y optimizado; 
     * se puede implementar en 3 o 4 líneas de código
     * Obligatorio utilizar el operador condicional ?
     * @param jp (panel donde está situado el tablero)
     * variable local String mensajeGanador
     */   
    public void mostrarVentanaGanador(javax.swing.JPanel jp){
        // Inserta código aquí...
        String gana= this.getTurno()==1? "X" : "O";
        //String gana= Integer.toString(this.getTurno());
        int opcion = JOptionPane.showConfirmDialog(jp,"Soy José Luis Vásquez Drouet, \ndoy la enhorabuena a las "
                + gana + "!!! \n¿Quiéres continuar jugando ?" , "Enhorabuena Ganador",JOptionPane.YES_NO_OPTION);
        
        if (opcion==1) System.exit(0);
    }
    /**
     * Deshabilitará el botón para evitar que se vuelva a posicionar una ficha en ese hueco
     * @param bt (Botón seleccionado)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     * @return 
     */
    public int tiradaJugador(javax.swing.JButton bt, int x, int y, 
            int matriz[][], javax.swing.JPanel jp, 
            javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        
        // Deshabilita el botón
        bt.setEnabled(false);
        bt.setContentAreaFilled(true);
        
        // Insertar la ficha en el botón
        ponerFicha(matriz,x,y,bt);  
        
        // Comprobar si se ha ganado la partida y mostrar la ventana con el
        // mensaje cuando corresponda
         if (comprobarJuego(matriz)==0)
             cambioTurno();
         else{
             ganador(lX,lO);
             this.mostrarVentanaGanador(jp);
             
              // Deshabilitar tablero
             habilitado= false;
             habilitarTablero(jp);
         }
        
         return turno;
    }
    
    /**
     * Actualizar la puntuación de cada jugador al ganar la partida
     * Al finalizar el incremento de puntuación es necesario cambiar de turno
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     */
    public void ganador(javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        
        // Incrementa jugador ganador e inserta resultado en jLabel    
       if (getTurno()==0){
           pX++;
           lX.setText(""+pX);
       } else {
           pO++;
           lO.setText(""+pO);
       }
       this.cambioTurno();
    }
    
    /**
     * Habilitará o deshabilitará el tablero dependiendo del estado de la variable habilitado
     * @param jp  (Panel dónde se sitúa el tablero de juego)
     */
    public void habilitarTablero( javax.swing.JPanel jp){
        // Inserta código aquí...
        // Bloquea todos los elementos del JPanel
        Component[] c = jp.getComponents();
        for (Component component : c){
            component.setEnabled(habilitado);
        }
        jp.setEnabled(habilitado); 
    }
    
    /**
     * En ponerFicha, Insertaremos la ficha en la posición correspondiente de la matriz
     * Llamaremos al método pintarFicha
     * @param matriz (Tablero de juego)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param bt (Botón pulsado)
     * turno  (para saber que ficha poner)
     */
    public void ponerFicha(int matriz[][], int x, int y, javax.swing.JButton bt){
        // Inserta código aquí...        

        // Insertar ficha en la posición de la matriz
        // mostrar la ficha correspondiente
        
        matriz[x][y]= this.getTurno();
        this.pintarFicha(bt);
        
    }
    
    /**
     * Pintará la ficha en el tablero de juego visual, es decir, en el botón
     * @param bt (Botón pulsado)
     */
    private void pintarFicha(javax.swing.JButton bt){
        // Inserta código aquí...
        // Si el turno es de 0 mostrará una X en rojo
        // Si el turno es de 1, mostrará una O en azul 
        if (getTurno()==0){
            bt.setText("X");
            bt.setForeground(Color.red);
        } else {
            bt.setText("O");
            bt.setForeground(Color.blue);
        }         

    }
    
    /**
     * Inicializa una nueva partida, reinicia la matriz (Tablero de juego) y habilita el tablero
     * 
     * ¡¡¡¡No es necesario modificar este método!!!!.
     * 
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void iniciarPartida(int matriz[][], javax.swing.JPanel jp){
        // Rellenamos la matriz por primera vez, evitando que se repitan los números
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                matriz[x][y]=(x+10)*(y+10);
            }
        }

        // Habilitar tablero
         habilitado = true;
         habilitarTablero(jp);
    }
}
