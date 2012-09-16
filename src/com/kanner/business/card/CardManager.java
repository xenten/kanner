package com.kanner.business.card;

import com.kanner.domain.Card;

public interface CardManager {

	public Card create(Card card);
	public Card read(long id);
	public Card update(Card card);
	public Card delete(Card card);
}
