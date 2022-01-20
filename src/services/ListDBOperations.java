package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Item;
import enums.Unidade;

public class ListDBOperations {
	
	public List<Item> readItemDB(String path, List<Item> dbItems) {
		//Método para inserir em uma List o banco de dados - arquivo TXT
		List<Item> bdItem = dbItems;
		List<Item> bdItemOrganized = new ArrayList<Item>();
		
		//Lê o arquivo
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();

			while (line != null) {	//Enquanto não for nulo
				String[] splited = line.split("#"); //Separa em uma array o que for encontrado antes do informado no split()
				//Abaixo, já adiciona à lista de "Item" os novos "Item" formados a partir do que for recebido do split
				bdItem.add(new Item(splited[0], Double.parseDouble(splited[1]), Unidade.valueOf(splited[2]))); 
				line = br.readLine();
			}
			//Collections.sort(bdItem);  //Organiza a lista chamando o método implementado da classe Item.java
			bdItemOrganized.clear(); //Limpa a lista que receberá a nova lista organizada - caso tenha sido utilizada em outro momento
			for(Item it : bdItem) {
				//System.out.println(it.toString());
				bdItemOrganized.add(it);
			}
				br.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return bdItemOrganized;
	}
	
	public void organizeItemDBFromFile(String path, List<Item> dbItems) {
		//Método para reorganizar os itens no arquivo de banco de dados - arquivo TXT
		// ele recupera todo o banco de dados, organiza e re-escreve o arquivo TXT
		List<Item> bdItem = dbItems;
		List<Item> bdItemOrganized = new ArrayList<Item>();
		
		//Lê o arquivo
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();

			while (line != null) {	//Enquanto não for nulo
				String[] splited = line.split("#"); //Separa em uma array o que for encontrado antes do informado no split()
				//Abaixo, já adiciona à lista de "Item" os novos "Item" formados a partir do que for recebido do split
				bdItem.add(new Item(splited[0], Double.parseDouble(splited[1]), Unidade.valueOf(splited[2]))); 
				line = br.readLine();
			}
			Collections.sort(bdItem);  //Organiza a lista chamando o método implementado da classe Item.java
			bdItemOrganized.clear(); //Limpa a lista que receberá a nova lista organizada - caso tenha sido utilizada em outro momento
			for(Item it : bdItem) {
				//System.out.println(it.toString());
				bdItemOrganized.add(it);
			}
			
			//Escreve no arquivo - sobrescrevendo.
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))){
				//bw.newLine();
				for(Item it : bdItemOrganized) {
					//System.out.println(it.toString());
					//Mantém o mesmo separador
					bw.write(it.getNomeDoItem() + "#" + it.getValorDoItem() + "#" + it.getUnidade());
					bw.newLine();
				}
				bw.flush();
				bw.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			br.close();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<Item> organizeItemDBFromList(List<Item> dbItems) {
		//Método para organizar a lista já capturada do arquivo TXT
		List<Item> bdItem = dbItems;
		List<Item> bdItemOrganized = new ArrayList<Item>();
		
		//Lê o arquivo
		try{
			Collections.sort(bdItem);  //Organiza a lista chamando o método implementado da classe Item.java
			for(Item it : bdItem) {
				//System.out.println(it.toString());
				bdItemOrganized.add(it);
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return bdItemOrganized;
	}
	
	public void addItemDB(String path, Item item) {
		//Método para adicionar item ao arquivo de banco de dados - arquivo TXT
		// SÓ É PERMITIDO UM ITEM COM CADA NOME, mas é possível burlar, inserindo um número no final, por exemplo.
		if(this.itemContainsDB(path, item.getNomeDoItem())) {
			System.out.println("Item já existente, só é permitida a alteração ou exclusão do item");
		}
		else {
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
				bw.write(item.getNomeDoItem() + "#" + item.getValorDoItem() + "#" + item.getUnidade());
				bw.newLine();
				bw.flush();
				bw.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean itemContainsDB(String path, String itemNome) {
		//Método para verificar se contém determinado item no arquivo de banco de dados - arquivo TXT
		// SÓ É PERMITIDO UM ITEM COM CADA NOME, mas é possível burlar, inserindo um número no final, por exemplo.
		
		//Lê o arquivo
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();

			while (line != null) {	//Enquanto não for nulo
				String[] splited = line.split("#"); //Separa em uma array o que for encontrado antes do informado no split()
				
				if(splited[0].equals(itemNome)) {
					//bdItem.add(new Item(splited[0], Double.parseDouble(splited[1]), Unidade.valueOf(splited[2])));
					return true;
				}
				//Abaixo, já adiciona à lista de "Item" os novos "Item" formados a partir do que for recebido do split
				line = br.readLine();
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean itemContainsDB(List<Item> listFromFileDB, String itemNome) {
		//Método para verificar se contém determinado item na lista recebida do arquivo de banco de dados - arquivo TXT
		// SÓ É PERMITIDO UM ITEM COM CADA NOME, mas é possível burlar, inserindo um número no final, por exemplo.
		
		//Lê o arquivo
		try{
			for(Item itm : listFromFileDB) {
				if(itm.getNomeDoItem().equalsIgnoreCase(itemNome)) {
					return true;
				}
			}
						
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<Item> listAllItensContainsListDB(List<Item> listFromFileDB, String itemNome) {
		//Método para verificar se contém determinado item na lista recebida do arquivo de banco de dados - arquivo TXT
		// SÓ É PERMITIDO UM ITEM COM CADA NOME, mas é possível burlar, inserindo um número no final, por exemplo.
		List<Item> listAllItensContainsListDBReturn = new ArrayList<Item>();
		//Lê o arquivo
		try{
			for(Item itm : listFromFileDB) {
				if(itm.getNomeDoItem().contains(itemNome)) {
					listAllItensContainsListDBReturn.add(itm);
				}
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return listAllItensContainsListDBReturn;
	}
	
	public Item getItemDB(List<Item> bdItem, String itemNome) {
		try{
			for(Item it : bdItem) {
				if(it.getNomeDoItem().equalsIgnoreCase(itemNome)) {
					return it;
				}
				//System.out.println(it.toString());
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
