package devops.hw1.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;
 
/**
 * The component that draws everything for the game.
 * @author malinocr
 *
 */
@SuppressWarnings("serial")
public class MTGComponent extends JComponent{
	private static final double BASE_HAND_CARDS_POSITION = 0;
	private static final double BASE_HAND1_CARDS_POSITION = 0.9;
	private static final double BASE_BATTLEFIELD_CARDS_POSITION = 0.1;
	private static final double BASE_BATTLEFIELD1_CARDS_POSITION = 0.8;
	private static final double CENTER_LINE_X_POSITION = 0.5;
	private static final int CENTER_LINE_Y_POSITION = 0;
	private static final double SIDEBAR_WIDTH = 0.1;
	private static final double HAND_HEIGHT = 0.8;
	private static final double EXILE_HEIGHT = 0.1;
	private static final double LIBRARY_HEIGHT = 0.1;
	private static final double SIDEBAR1_X_POSITION = 0;
	private static final double SIDEBAR2_X_POSITION = 0.9;
	private static final int HAND_Y_POSITION = 0;
	private static final double SIDEBAR_ADJUSTMENT  = 0.5;
	private static final double ZONES_MAX_FONT_WIDTH = 0.08;
	private static final double ZONES_MAX_FONT_HEIGHT = 0.08;
	private static final double LIBRARY_COUNT_X_POSITION = 0.038;
	private static final double LIBRARY_COUNT_Y_POSITION = 0.98;
	private static final double GRAVEYARD_COUNT_X_POSITION = 0.01;
	private static final double GRAVEYARD_COUNT_Y_POSITION = 0.88;
	private static final double EXILE_COUNT_X_POSITION = 0.06;
	private static final double LIBRARY1_COUNT_X_POSITION = 0.938;
	private static final double GRAVEYARD1_COUNT_X_POSITION = 0.91;
	private static final double EXILE1_COUNT_X_POSITION = 0.96;
	private static final double CARD_WIDTH_TO_HEIGHT_RATIO = 3.5/2.5;
	private static final double HAND_BUFFER_FROM_TOP = 0.025;
	private static final double HAND_BUFFER_FROM_LEFT = 0.05;
	private static final double BUFFER_FROM_TOP_OF_PREV_CARD = 1.25;
	private static final double MINIMUM_HAND_CARD_WIDTH = 0.08;
	private static final double CARD_HEIGHT_TO_WIDTH_RATIO = 2.5/3.5;
	private static final int ABILITY_FONT_SIZE = 25;
	private static final double ABILITY_STRING_LEFT_BUFFER = 0.1;
	private static final double ABILITY_STRING_Y_CENTER_SHIFT = 0.05;
	
	private int windowX;
	private int windowY;
	private ArrayList<GUICard> handGUICards1;
	private DispGUICard dispGUICard1;
	private ArrayList<GUICard> handGUICards2;
	private DispGUICard dispGUICard2;
	private ArrayList<GUICard> battleGUICards1;
	private ArrayList<GUICard> battleGUICards2;
	
	/**
	 * Constructor for a MTGComponent
	 * @param x width of the usable space in the JFrame that the component is placed in
	 * @param y height of the usable space in the JFrame that the component is placed in
	 */
	public MTGComponent(int x, int y){
		this.windowX = x;
		this.windowY = y;
		this.handGUICards1 = new ArrayList<GUICard>();
		this.dispGUICard1 = null;
		this.handGUICards2 = new ArrayList<GUICard>();
		this.dispGUICard2 = null;
		this.battleGUICards1 = new ArrayList<GUICard>();
		this.battleGUICards2 = new ArrayList<GUICard>();
	}
	
	/**
	 * Get GUI Cards in first player's hand
	 * @return GUI cards
	 */
	public ArrayList<GUICard> getHandGUICards1() {
		return handGUICards1;
	}

	/**
	 * Get GUI Cards in second player's hand
	 * @return GUI cards
	 */
	public ArrayList<GUICard> getHandGUICards2() {
		return handGUICards2;
	}

	/**
	 * Get GUI Cards on first player's battlefield
	 * @return GUI cards
	 */
	public ArrayList<GUICard> getBattleGUICards1() {
		return battleGUICards1;
	}

	/**
	 * Get GUI Cards on second player's battlefield
	 * @return GUI cards
	 */
	public ArrayList<GUICard> getBattleGUICards2() {
		return battleGUICards2;
	}

	/**
	 * Set the current width of the window the component is in
	 * @param x width of the window
	 */
	public void setWindowX(int x){
		this.windowX = x;
	}
	
	/**
	 * Set the current height of the window the component is in
	 * @param x height of the window
	 */
	public void setWindowY(int y){
		this.windowY = y;
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;
		drawFormat(graphics2);
		drawCountedZones1(graphics2);
		drawCountedZones2(graphics2);
		generateGUICards(this.handGUICards1, Zone.HAND, BASE_HAND_CARDS_POSITION);
		generateGUICards(this.handGUICards2, Zone.HAND1, BASE_HAND1_CARDS_POSITION);
		generateGUICards(this.battleGUICards1, Zone.BATTLE_FIELD, BASE_BATTLEFIELD_CARDS_POSITION);
		generateGUICards(this.battleGUICards2, Zone.BATTLE_FIELD1, BASE_BATTLEFIELD1_CARDS_POSITION);
		if(this.dispGUICard1 != null){
			generateDispGUICard1(this.dispGUICard1.getCard());
		}
		if(this.dispGUICard2 != null){
			generateDispGUICard2(this.dispGUICard2.getCard());
		}
		drawGUICards(graphics2);
	}
	
	/**
	 * Draws the basic format for the board
	 * @param graphics2
	 */
	private void drawFormat(Graphics2D graphics2) {
		graphics2.setColor(Color.BLACK);
		graphics2.drawLine((int)(windowX*CENTER_LINE_X_POSITION), CENTER_LINE_Y_POSITION, (int)(windowX*CENTER_LINE_X_POSITION), windowY);
		
		graphics2.draw(new Rectangle((int)SIDEBAR1_X_POSITION,HAND_Y_POSITION,(int)(windowX*SIDEBAR_WIDTH), (int)(windowY*HAND_HEIGHT)));
		graphics2.draw(new Rectangle((int)SIDEBAR1_X_POSITION,(int)(windowY*HAND_HEIGHT),(int)(windowX*(SIDEBAR_WIDTH/2)),(int)(windowY*EXILE_HEIGHT  + SIDEBAR_ADJUSTMENT)));
		graphics2.draw(new Rectangle((int)(SIDEBAR1_X_POSITION + windowX*(SIDEBAR_WIDTH/2)),(int)(windowY*HAND_HEIGHT),(int)(windowX*(SIDEBAR_WIDTH/2) + SIDEBAR_ADJUSTMENT),(int)(windowY*EXILE_HEIGHT  + SIDEBAR_ADJUSTMENT)));
		graphics2.draw(new Rectangle((int)SIDEBAR1_X_POSITION,(int)(windowY*(HAND_HEIGHT + EXILE_HEIGHT)),(int)(windowX*SIDEBAR_WIDTH),(int)(windowY*LIBRARY_HEIGHT)));
		
		graphics2.draw(new Rectangle((int)(SIDEBAR2_X_POSITION*windowX),HAND_Y_POSITION,(int)(windowX*SIDEBAR_WIDTH), (int)(windowY*HAND_HEIGHT)));
		graphics2.draw(new Rectangle((int)(SIDEBAR2_X_POSITION*windowX),(int)(windowY*HAND_HEIGHT),(int)(windowX*(SIDEBAR_WIDTH/2) + SIDEBAR_ADJUSTMENT),(int)(windowY*EXILE_HEIGHT  + SIDEBAR_ADJUSTMENT)));
		graphics2.draw(new Rectangle((int)(SIDEBAR2_X_POSITION*windowX + windowX*(SIDEBAR_WIDTH/2)),(int)(windowY*HAND_HEIGHT),(int)(windowX*(SIDEBAR_WIDTH/2)),(int)(windowY*EXILE_HEIGHT  + SIDEBAR_ADJUSTMENT)));
		graphics2.draw(new Rectangle((int)(SIDEBAR2_X_POSITION*windowX),(int)(windowY*(HAND_HEIGHT + EXILE_HEIGHT)),(int)(windowX*SIDEBAR_WIDTH),(int)(windowY*LIBRARY_HEIGHT)));
	}
	
	/**
	 * Draws counted library, graveyard, and exile for player 1
	 * @param graphics2
	 */
	private void drawCountedZones1(Graphics2D graphics2){
		graphics2.setFont(new Font("TimesRoman", Font.PLAIN, Math.min((int)(windowY*ZONES_MAX_FONT_HEIGHT), (int)(windowX*ZONES_MAX_FONT_WIDTH)))); 
		graphics2.drawString(String.valueOf(Zone.LIBRARY.getSize()),(int)(windowX*LIBRARY_COUNT_X_POSITION),(int)(windowY*LIBRARY_COUNT_Y_POSITION));
		graphics2.drawString(String.valueOf(Zone.GRAVEYARD.getSize()),(int)(windowX*GRAVEYARD_COUNT_X_POSITION),(int)(windowY*GRAVEYARD_COUNT_Y_POSITION));
		graphics2.drawString(String.valueOf(Zone.EXILE.getSize()),(int)(windowX*EXILE_COUNT_X_POSITION),(int)(windowY*GRAVEYARD_COUNT_Y_POSITION));
	}
	
	/**
	 * Draws counted library, graveyard, and exile for player 2
	 * @param graphics2
	 */
	private void drawCountedZones2(Graphics2D graphics2){
		graphics2.setFont(new Font("TimesRoman", Font.PLAIN, Math.min((int)(windowY*ZONES_MAX_FONT_HEIGHT), (int)(windowX*ZONES_MAX_FONT_WIDTH)))); 
		graphics2.drawString(String.valueOf(Zone.LIBRARY1.getSize()),(int)(windowX*LIBRARY1_COUNT_X_POSITION),(int)(windowY*LIBRARY_COUNT_Y_POSITION));
		graphics2.drawString(String.valueOf(Zone.GRAVEYARD1.getSize()),(int)(windowX*GRAVEYARD1_COUNT_X_POSITION),(int)(windowY*GRAVEYARD_COUNT_Y_POSITION));
		graphics2.drawString(String.valueOf(Zone.EXILE1.getSize()),(int)(windowX*EXILE1_COUNT_X_POSITION),(int)(windowY*GRAVEYARD_COUNT_Y_POSITION));
	}
	
	/**
	 * Generates a GUICard object for each card in a given zone and puts them into a given arraylist
	 * @param cardsAL an array list to place the created GUICard objects
	 * @param zone the zone where the MTGCards are be created from
	 * @param baseXLocation the base x percentage of the width of the screen where the cards rectangle should be placed 
	 */
	private void generateGUICards(ArrayList<GUICard> cardsAL, Zone zone, double baseXLocation){//!#stress-test this
		cardsAL.clear();
		int height = getCardHeight(zone);
		int width = (int)Math.round(height * CARD_WIDTH_TO_HEIGHT_RATIO);
		int currentSpace = (int)Math.round(HAND_BUFFER_FROM_TOP*windowY);
		Card[] cards = zone.getCards();
		for(int i = 0; i < zone.getSize(); i++){
			cardsAL.add(new GUICard(new Rectangle((int)((HAND_BUFFER_FROM_LEFT + baseXLocation)*windowX - width/2), currentSpace, width, height), cards[i]));
			currentSpace = currentSpace + (int)(BUFFER_FROM_TOP_OF_PREV_CARD*height);
		}
	}
	
	/**
	 * Draw all the GUICards onto the graphics object
	 * @param graphics2 the graphics object to draw onto
	 */
	private void drawGUICards(Graphics2D graphics2){
		drawGUICardArrayList(handGUICards1, graphics2);
		drawGUICardArrayList(handGUICards2, graphics2);
		drawGUICardArrayList(battleGUICards1, graphics2);
		drawGUICardArrayList(battleGUICards2, graphics2);
		drawDispGUICard(dispGUICard1, graphics2);
		drawDispGUICard(dispGUICard2, graphics2);
	}

	/**
	 * Draw all the GUICards in a given arraylist
	 * @param cardsAL the arraylist where the cards to draw are located
	 * @param graphics2 the graphics object to draw onto
	 */
	private void drawGUICardArrayList(ArrayList<GUICard> cardsAL, Graphics2D graphics2 ){
		for(int i = 0; i < cardsAL.size(); i++){
			graphics2.draw(cardsAL.get(i).getRec());
			graphics2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			graphics2.drawString(cardsAL.get(i).getCard().getName(), (int)cardsAL.get(i).getRec().getCenterX(), (int)cardsAL.get(i).getRec().getCenterY());
		}
	}
	
	/**
	 * Determine the height a card in a given zone should be based on the window height
	 * @param zone the zone the card is located in
	 * @return the height of the card
	 */
	private int getCardHeight(Zone zone){
		int HeightRec = 3*windowY/(5*zone.getSize()-1);
		int heightWidthRec = (int)(HeightRec * CARD_WIDTH_TO_HEIGHT_RATIO);
		int windowWidthRec = (int)(windowX*MINIMUM_HAND_CARD_WIDTH);
		if(heightWidthRec < windowWidthRec){
			return HeightRec;
		} else {
			return (int)Math.round(windowWidthRec * CARD_HEIGHT_TO_WIDTH_RATIO);
		}
	}
	
	/**
	 * Draws the displayed GUI card
	 * @param dispCard display card object to be drawn
	 * @param graphics2 graphics to draw the card on
	 */
	private void drawDispGUICard(DispGUICard dispCard, Graphics2D graphics2) {
		if(dispCard != null){
			graphics2.draw(dispCard.getRec());
			graphics2.setFont(new Font("Futura", Font.PLAIN, ABILITY_FONT_SIZE));
			graphics2.drawString(dispCard.getCard().getName(), (int)dispCard.getRec().getCenterX(), (int)dispCard.getRec().getCenterY());
			for(int i = 0; i < dispCard.getAbilityBoxes().length; i++){
				graphics2.draw(dispCard.getAbilityBoxes()[i]);
				graphics2.drawString(dispCard.getAbilityStrings()[i], (int)(dispCard.getAbilityBoxes()[i].getX() + (ABILITY_STRING_LEFT_BUFFER * dispCard.getAbilityBoxes()[i].getWidth())), (int)(dispCard.getAbilityBoxes()[i].getCenterY() + (ABILITY_STRING_Y_CENTER_SHIFT * dispCard.getAbilityBoxes()[i].getHeight())));
			}
		}
	}
	
	String[] test = {"Cast this spell", "Ability 1"};
	private static final double DISPLAY_CARD1_X_POSITION = 0.15;
	private static final double DISPLAY_CARD1_Y_POSITION = 0.2;
	/**
	 * Create a Displayed GUI card object for the first player
	 * @param card card being displayed
	 */
	public void generateDispGUICard1(Card card){
		this.dispGUICard1 = new DispGUICard(new Rectangle((int)(DISPLAY_CARD1_X_POSITION*windowX), (int)(DISPLAY_CARD1_Y_POSITION*windowY), (int)(0.2*windowX), (int)(0.2*windowX*(3.5/2.5))),card, test);
	}
	
	/**
	 * Create a Displayed GUI card object for the second player
	 * @param card card being displayed
	 */
	public void generateDispGUICard2(Card card){
		this.dispGUICard2 = new DispGUICard(new Rectangle((int)(0.55*windowX), (int)(0.2*windowY), (int)(0.2*windowX), (int)(0.2*windowX*(3.5/2.5))),card, test);
	}
	
	/**
	 * Get the current Displayed GUI card for the first player
	 * @return current displayed card
	 */
	public DispGUICard getDispGUICard1() {
		return dispGUICard1;
	}

	/**
	 * Get the current Displayed GUI card for the second player
	 * @return current displayed card
	 */
	public DispGUICard getDispGUICard2() {
		return dispGUICard2;
	}

	/**
	 * Set the current Displayed GUI card for the first player
	 * @param dispGUICard1 
	 */
	public void setDispGUICard1(DispGUICard dispGUICard1) {
		this.dispGUICard1 = dispGUICard1;
	}

	/**
	 * Set the current Displayed GUI card for the second player
	 * @param dispGUICard2
	 */
	public void setDispGUICard2(DispGUICard dispGUICard2) {
		this.dispGUICard2 = dispGUICard1;
	}
}