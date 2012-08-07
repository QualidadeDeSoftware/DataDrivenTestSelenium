package br.com.qualidadedesoftware.test.home;

import java.io.File;

import org.junit.Test;

import br.com.qualidadedesoftware.configuration.SeleniumTestCase;
import br.com.qualidadedesoftware.configuration.Suporte;

public class Busca extends SeleniumTestCase {
	
	@Test
	public void testBuscaSemPlanilha() throws Exception {
		selenium.open("/home");
		selenium.type("id=q", "ivete sangalo");
		selenium.click("id=submitbutton");
		
		for (int second = 0;; second++) {
			if (second >= 5) fail("timeout");
			try { if (selenium.isTextPresent("Você buscou por ivete sangalo")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
	}
	
	@Test
	public void testBuscaComPlanilha() throws Exception {
		Suporte suporte = new Suporte(new File("pom.xml").getAbsolutePath().replace("pom.xml", "src\\test\\resource\\datapool\\Buscas.xls"));
		
		for (int i = 1; i < suporte.getLinhas(); i++, suporte.setLinhaAtual(suporte.getLinhaAtual() +1)) {
			selenium.open("/home");
			selenium.type("id=q", suporte.datapool("Artista"));
			selenium.click("id=submitbutton");
			
			for (int second = 0;; second++) {
				if (second >= 5) fail("timeout");
				try { if (selenium.isTextPresent("Você buscou por " + suporte.datapool("Artista"))) break; } catch (Exception e) {}
				Thread.sleep(1000);
			}
		}
	}
	
}
