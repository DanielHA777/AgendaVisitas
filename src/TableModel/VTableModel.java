package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Visita;
import Model.Visitante;

public class VTableModel extends AbstractTableModel{
	 private List<Visita> visitante;
	    private final String[] COLUNAS  =  {"data", "Residente", "Visitante1", "visitante2", "visitante3", "visitante4" ,"hora", "Obs"};
	    public VTableModel(List<Visita> lista) {
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
			Visita c = visitante.get(rowIndex);
			switch (columnIndex) {  
			case 0: 
				return c.getData();
			case 1: 
				return c.getNomeResi();
			case 2:
				return c.getVisitante1();
			case 3:
				return c.getVisitante2();
			case 4: 
				return c.getVisitante3();
			case 5: 
				return c.getVisitante4();
			case 6: 
				return c.getHora();
			case 7:
				return c.getObservacao();
			}
			return null;
		}
		@Override
		public String getColumnName(int column) {
			return COLUNAS[column]; 
		}
		
		public Visita getLine(int linha){
		    return this.visitante.get(linha);
		}
}