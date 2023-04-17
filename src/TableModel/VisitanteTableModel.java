package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Residente;
import Model.Visitante;

public class VisitanteTableModel extends AbstractTableModel{
	 private List<Visitante> visitante;
	    private final String[] COLUNAS  =  {"Visitante", "Residente"};
	    public VisitanteTableModel(List<Visitante> lista) {
	    	this.visitante = lista;
		}   
		
		@Override
		public int getRowCount() {     
			return visitante.size();
		}

		@Override
		public int getColumnCount() { 
			return COLUNAS.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) { 
			Visitante c = visitante.get(rowIndex);
			switch (columnIndex) {  
			case 0: 
				return c.getNome();
			case 1: 
				return c.getNomeResi();
			}
			return null;
		}
		@Override
		public String getColumnName(int column) {
			return COLUNAS[column]; 
		}
}