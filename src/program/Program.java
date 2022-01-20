package program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Item;
import enums.Unidade;
import services.ListDBOperations;

public class Program {

	public static void main(String[] args) {
		
		List<Item> bdItem = new ArrayList<Item>();
		String path = "C:\\Users\\gabu_\\Desktop\\itens.txt";
		
		
		/*PARA SER EXECUTADO APENAS UMA VEZ
		try {
			List<String> listaNomesItens = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Gabriel\\Desktop\\NomesProdutos.txt"));
			String nomes = br.readLine();
			while (nomes != null) {
				listaNomesItens.add(nomes);
				nomes = br.readLine();
			}
//			System.out.println(listaNomesItens);
//			System.out.println(listaNomesItens.size());
			br.close();
			

			List<Double> listaValoresItens = new ArrayList<>();
			br = new BufferedReader(new FileReader("C:\\Users\\Gabriel\\Desktop\\PrecosProdutos.txt"));
			Double precos;
			String value = br.readLine().replace(",", ".");
			try {
				precos = Double.parseDouble(value);
			} catch (Exception e) {
				precos = 0.1;
			}
			while (value != null) {
				listaValoresItens.add(precos);
				//System.out.println(value);
				value = br.readLine();
				if(value != null) {
					value = value.replace(",", ".");
					precos = Double.parseDouble(value);
				}
			}
//			System.out.println(listaValoresItens);
//			System.out.println(listaValoresItens.size());
			br.close();
			
			
			List<Unidade> listaUnidadeItens = new ArrayList<>();
			br = new BufferedReader(new FileReader("C:\\Users\\Gabriel\\Desktop\\UnidadesProdutos.txt"));
			Unidade unidade;
			String stgunidade = br.readLine();
			try {
				unidade = Unidade.valueOf(stgunidade);
			} catch (Exception e) {
				unidade = Unidade.KILOS;
			}
			while (stgunidade != null) {
				listaUnidadeItens.add(unidade);
				stgunidade = br.readLine();
				if(stgunidade != null) {
					unidade = Unidade.valueOf(stgunidade);					
				}
				
			}
//			System.out.println(listaUnidadeItens);
//			System.out.println(listaUnidadeItens.size());
			br.close();
			
			

			ListDBOperations ldbo = new ListDBOperations();
			bdItem = ldbo.readItemDB(path, bdItem);
			
			
			for(int i = 0; i< listaNomesItens.size(); i++) {
				if (!ldbo.itemContainsDB(bdItem, listaNomesItens.get(i))) {
					ldbo.addItemDB(path, new Item(listaNomesItens.get(i), listaValoresItens.get(i), listaUnidadeItens.get(i)));
					System.out.println(listaNomesItens.get(i) + " adicionado!");
				}
				else {
					System.out.println("Item já incluído no BDtxt");
				}
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		PARA SER EXECUTADO APENAS UMA VEZ*/
		
		
		ListDBOperations ldbo = new ListDBOperations();
		long startTime = System.nanoTime();
		bdItem = ldbo.readItemDB(path, bdItem);
		System.out.println(System.nanoTime() - startTime);
		

		startTime = System.nanoTime();
		for(Item it : bdItem) {
			System.out.println(it.toString());
		}
		System.out.println(System.nanoTime() - startTime);
		
		//startTime = System.nanoTime();
		//ldbo.addItemDB(path, new Item("Laranja", 2.5, Unidade.KILOS));
		//ldbo.addItemDB(path, new Item("mamão", 4.25, Unidade.UNIDADES));
		//System.out.println(System.nanoTime() - startTime);
		
		System.out.println(" ________ ");

		startTime = System.nanoTime();
		if (ldbo.itemContainsDB(bdItem, "Laranja")) {
			System.out.println("Terminado!");
		}
		else {
			System.out.println("VIXE, não achou!");
		}

		System.out.println("From List: " + (System.nanoTime() - startTime));
		

		startTime = System.nanoTime();
		if (ldbo.itemContainsDB(path, "Laranja")) {
			System.out.println("Terminado!");
		}
		else {
			System.out.println("VIXE, não achou!");
		}

		System.out.println("From Txt: " + (System.nanoTime() - startTime));
		
		
		
		startTime = System.nanoTime();
		bdItem = ldbo.readItemDB(path, bdItem = new ArrayList<Item>());
		for(Item it : bdItem) {
			System.out.println(it.toString());
		}
		System.out.println(ldbo.listAllItensContainsListDB(bdItem, "a"));
		System.out.println(System.nanoTime() - startTime);
		System.out.println();
		
		startTime = System.nanoTime();
		Item leite = ldbo.getItemDB(bdItem, "Sal");
		System.out.println("*** Got: "+leite.toString());
		if(leite.getValorDoItem()<1) {
			System.out.println("Damn, so cheat!");
		}
		else {
			System.out.println("Not so cheap.. it's getting expensive..");
		}
		System.out.println(System.nanoTime() - startTime);
		//ldbo.organizeItemDBFromFile(path, bdItem);
	}

}
