/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quindio (Armenia - Colombia)
 * Programa de Ingenieria de Sistemas y Computacion
 * Abril 2023
 * Asignatura: Teoria de Lenguajes Formales
 * Fase 2 Proyecto Final: AnalizadorLexico
 * Autores: Juan Sebastian Corredor Cabrera y Frank Soto Paz
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package interfaz;
/**
 * Panel para ingresar texto al analizador lexico
 */


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelEntradaCodigo extends JPanel implements ActionListener
{

    // -----------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------

    private static final String VERTOKENS = "VER TOKENS";

    // -----------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------

    /**
     * Ventana principal
     */
    private InterfazAnalizadorLexico ventanaPrincipal;

    /**
     * Etiqueta codigo
     */
    private JLabel etiquetaCodigo;

     /**
     * Campo donde se ingresa el codigo fuente
     */
    private JTextField campoCodigo;

     /**
     * Boton ver tokens
     */
    private JButton botonVerTokens;

    // -----------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------

    /**
     * Constructor del panel.
     * @param principal Ventana principal. principal != null.
     */
    public PanelEntradaCodigo( InterfazAnalizadorLexico principal )
    {
        ventanaPrincipal = principal;
        setLayout( new GridLayout( 1, 3 ) );

        etiquetaCodigo = new JLabel( "Codigo fuente que se va a analizar: " );
        campoCodigo = new JTextField( 20);
        botonVerTokens = new JButton( "Ver tokens" );
        botonVerTokens.addActionListener( this );
        botonVerTokens.setActionCommand( VERTOKENS );

        add( etiquetaCodigo );
        add( campoCodigo );
        add( botonVerTokens );
    }

    // -----------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------

     /**
     * Responde ante las acciones en el panel.
     * @param e Evento que genera la accion.
     */
    public void actionPerformed( ActionEvent e )
    {
//        if( e.getActionCommand( ) == VERTOKENS )
      if( e.getActionCommand( ).equals(VERTOKENS)  )
        {
        	ventanaPrincipal.verTokens(campoCodigo.getText());
        }

    }
}
