package br.com.lopessolutions.base.util;

/**
 * 
 * @author Thaison
 *
 */
public interface ContabilAction extends java.io.Serializable {

	public void setTela(String tela);

	public void execute() throws Exception;

}
