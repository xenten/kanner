package com.kanner.business.card;

import java.util.List;

import com.kanner.domain.Card;

@Deprecated
public interface CardManager {

	public Card create(Card card);
	public Card read(String id);
	public Card update(Card card);
	public Card delete(Card card);
	public List<Card> list();
}
