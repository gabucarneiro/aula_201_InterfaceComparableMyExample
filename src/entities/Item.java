package entities;

import enums.Unidade;

public class Item implements Comparable<Item>{

	private String nomeDoItem;
	private Double valorDoItem;
	private Unidade unidade;
			
	public Item() {
	}

	public Item(String nomeDoItem, Double valorDoItem, Unidade unidade) {
		this.nomeDoItem = nomeDoItem;
		this.valorDoItem = valorDoItem;
		this.unidade = unidade;
	}

	public String getNomeDoItem() {
		return nomeDoItem;
	}

	public void setNomeDoItem(String nomeDoItem) {
		this.nomeDoItem = nomeDoItem;
	}

	public Double getValorDoItem() {
		return valorDoItem;
	}

	public void setValorDoItem(Double valorDoItem) {
		this.valorDoItem = valorDoItem;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	@Override
	public int compareTo(Item o) {
		return this.nomeDoItem.compareToIgnoreCase(o.getNomeDoItem());
	}

	@Override
	public String toString() {
		return "Nome: " + nomeDoItem + " - Valor: R$" + valorDoItem + " - Unidade: " + unidade;
	}
}
