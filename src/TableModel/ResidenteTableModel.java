package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Residente;


public class ResidenteTableModel extends AbstractTableModel{
	 private List<Residente> residente;
	    private final String[] COLUNAS  =  {"nome", "quarto"};
	    public ResidenteTableModel(List<Residente> lista) {
	    	this.residente = lista;
		}   
		
		@Override
		public int getRowCount() {     
			return residente.size();
		}

		@Override
		public int getColumnCount() { 
			return COLUNAS.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) { 
			Residente c = residente.get(rowIndex);
			switch (columnIndex) {  
			case 0: 
				return c.getNome();
			case 1: 
				return c.getQuarto();
			}
			return null;
		}
		@Override
		public String getColumnName(int column) {
			return COLUNAS[column]; 
		}
}
