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

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Dialogo para mostrar los tokens
 */
public class DialogoTokens extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel usado para contener la lista
     */
    private JScrollPane scrollDesplazamiento;

    /**
     * La lista donde se muestran los tokens
     */
    private JList listaTokens;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Dialogo donde se muestran los tokens
     */
    public DialogoTokens( )
    {
        setBackground( Color.white );
        setTitle( "Tokens" );
        scrollDesplazamiento = new JScrollPane( );

        // Lista donde se almacenaran los tokens
        listaTokens = new JList( );
        listaTokens.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION );

        // Scroll que desplegara la lista de tokens
        scrollDesplazamiento.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollDesplazamiento.setViewportView( listaTokens );
        add( scrollDesplazamiento );
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Modifica la lista de tokens mostrada
     * @param vectorTokensEditados La lista con la descripcion de los tokens que
     * se van a mostrar en la lista
     */
    public void cambiarListaTokens( ArrayList vectorTokensEditados )
    {
        listaTokens.setListData( vectorTokensEditados.toArray( ) );
    }
}
