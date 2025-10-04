package br.com.lopessolutions.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import br.com.lopessolutions.base.exception.AltException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 * 
 * @author Thaison
 *
 */
public class ArquivoUpload implements Serializable {

	private static final long serialVersionUID = -2307645553201187579L;

	private static final int fileLimitPadrao = 1;

	private List<Arquivo> listaArquivos;
	private int fileLimit; 

	public ArquivoUpload() {
		fileLimit = fileLimitPadrao;
	}

	public List<Arquivo> getListaArquivos() {
		if (listaArquivos == null) {
			listaArquivos = new ArrayList<>();
		}
		return listaArquivos;
	}

	public void setListaArquivos(List<Arquivo> listaArquivos) {
		this.listaArquivos = listaArquivos;
	}

	public int getFileLimit() {
		return fileLimit;
	}

	public void setFileLimit(int fileLimit) {
		this.fileLimit = fileLimit;
	}

	public void fileUploadAction(FileUploadEvent event) {
		try {

			UploadedFile f = event.getFile();

			if (f != null) {

				InputStream item = f.getInputStream();

				byte[] buffer = getBytes(item);

				Arquivo file = new Arquivo();

				if (buffer != null) {

					file.setId(getProxID());
					file.setNome(f.getFileName());
					file.setTipo(f.getContentType());
					file.setLength(buffer.length);
					file.setData(buffer);

					listaArquivos.add(file);
					fileLimit = fileLimit - 1; 
				}

			}

		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), ""));
		}
	}

	public byte[] getBytes(InputStream is) {
		try {

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			byte[] buffer = new byte[2048];

			for (int len; (len = is.read(buffer)) != -1;) {
				os.write(buffer, 0, len);
			}

			os.flush();

			return os.toByteArray();
		} catch (IOException e) {
			return null;
		}
	}

	public StreamedContent getMostrarPrimImagem() {

		StreamedContent sc;

		if (listaArquivos != null && !listaArquivos.isEmpty() && listaArquivos.get(0).getData() != null) {

			sc = DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
				try {

					if (listaArquivos.get(0).getData() != null && listaArquivos.get(0).getData().length > 0) {
						return new ByteArrayInputStream(listaArquivos.get(0).getData());
					} else {
						return null;
					}

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}).build();

		} else {
			sc = null;
		}

		return sc;

	}

	public StreamedContent getMostrarImagem(byte[] arquivo) {

		StreamedContent sc;

		if (arquivo != null) {
			sc = DefaultStreamedContent.builder().contentType("image/png").stream(() -> {
				try {

					return new ByteArrayInputStream(arquivo);

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}).build();
			;
		} else {
			sc = null;
		}

		return sc;

	}

	public int getProxID() {
		if (getListaArquivos().size() > 0) {
			return getListaArquivos().size() + 1;
		} else {
			return 1;
		}
	}

	public boolean isADados() {
		if (!getListaArquivos().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public int getSize() {
		if (getListaArquivos().size() > 0) {
			return getListaArquivos().size();
		} else {
			return 0;
		}
	}

	public byte[] getArquivo(int index) {
		if (!getListaArquivos().isEmpty()) {
			return getListaArquivos().get(index).getData();
		} else {
			return null;
		}
	}

	public void addArquivo(byte[] arquivo) {

		Arquivo file = new Arquivo();

		if (arquivo != null) {

			if (fileLimit > 0) {

				file.setId(getProxID());
				file.setNome("Arquivo-" + getProxID());
				file.setTipo("");
				file.setLength(arquivo.length);
				file.setData(arquivo);

				listaArquivos.add(file);
				fileLimit = fileLimit - 1;

			} else {
				throw new AltException("Número de arquivos maior que o total avaliado.");
			}
		}

	}

	public void clear() {
		getListaArquivos().clear();
		setFileLimit(fileLimitPadrao);
	}

	public void clear(int fileLimit) {
		getListaArquivos().clear();
		setFileLimit(fileLimit);
	}

	public class Arquivo implements Serializable {

		private static final long serialVersionUID = -8590953111756280829L;

		private int id;
		private String nome, tipo;
		private long length;
		private byte[] data;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public long getLength() {
			return length;
		}

		public void setLength(long length) {
			this.length = length;
		}

		public byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}

	}

 

}
