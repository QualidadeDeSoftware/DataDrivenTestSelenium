package br.com.qualidadedesoftware.configuration;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import org.junit.Ignore;

import com.thoughtworks.selenium.SeleneseTestCase;

@Ignore
public class Suporte extends SeleneseTestCase {
	String FILE_XLS = "null";
	String ENCODING_FILE_XLS = "Cp1252";
	public static int ABA_PLANILHA = 1;
	Workbook workbook = null;
	WorkbookSettings ws = null;
	File fileXML = null;
	Sheet sheet = null;
	int linhas = 0;
	int colunas = 0;
	int linhaAtual = 1;

	public Suporte() throws Exception {
		super();
		if (!FILE_XLS.equalsIgnoreCase("null")) {
			datapool("carga");
		}
	}
	
	public Suporte(String FILE_XLS) throws Exception {
		super();
		this.FILE_XLS = FILE_XLS;
		datapool("carga");
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhaAtual(int linhaAtual) {
		this.linhaAtual = linhaAtual;
	}

	public int getLinhaAtual() {
		return linhaAtual;
	}

	public String datapool(String coluna) throws BiffException, IOException {

		if ("carga".equalsIgnoreCase(coluna)) {
			fileXML = new File(FILE_XLS);

			if ("null".equalsIgnoreCase(FILE_XLS)) {
				fail("Informe o diretório e o nome do arquivo .xls na variável \"FILE_XLS\". Exemplo: \"C:/datapool.xls\"");
				if (fileXML.exists()) {
					fail("Não existe o arquivo .xml dentro do diretório informado!");
				}
			}

			try {
				ws = new WorkbookSettings();
				ws.setEncoding(ENCODING_FILE_XLS);
				workbook = Workbook.getWorkbook(fileXML, ws);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sheet = workbook.getSheet(ABA_PLANILHA - 1);
			linhas = sheet.getRows();
			colunas = sheet.getColumns();
		}
		if ("carga".equalsIgnoreCase(coluna))
			return null;

		for (int i = 0; i < colunas; i++) {
			Cell colunasXML = sheet.getCell(i, 0);
			if (coluna.equalsIgnoreCase(colunasXML.getContents()))
				return sheet.getCell(i, linhaAtual).getContents();
		}
		fail("A coluna \"" + coluna + "\" não exite no arquivo excel!");
		return null;
	}
	
}
