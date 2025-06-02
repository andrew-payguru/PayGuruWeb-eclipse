package za.co.payguru.model;

public class GraphData {
	private String indepVariable;
	private int depVariable;
	public GraphData(String indepVariable, int depVariable) {
		super();
		this.indepVariable = indepVariable;
		this.depVariable = depVariable;
	}
	public GraphData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getIndepVariable() {
		return indepVariable;
	}
	public void setIndepVariable(String indepVariable) {
		this.indepVariable = indepVariable;
	}
	public int getDepVariable() {
		return depVariable;
	}
	public void setDepVariable(int depVariable) {
		this.depVariable = depVariable;
	}
	@Override
	public String toString() {
		return "GraphData [indepVariable=" + indepVariable + ", depVariable=" + depVariable + "]";
	}
	
	
}
