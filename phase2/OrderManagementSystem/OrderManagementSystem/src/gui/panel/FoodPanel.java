package gui.panel;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.MainSystem;
import factory.FoodFactory;
import type.EnumInfo.SizeTypeEnum;
import type.food.Burgers;
import type.food.Desserts;
import type.food.Drinks;
import type.food.Salads;
import type.food.Snacks;
import type.Order;
import utils.ConstUtils;
import utils.FuncUtils;
import utils.LogUtils;

import javax.swing.JSeparator;

import java.awt.Color;

public class FoodPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1105913291408310595L;

	private JTextField textField_DietCoke;
	private JTextField textField_Coke;
	private JTextField textField_Orangejuice;
	private JTextField textField_ChickenNugget;
	private JTextField textField_BananaChocolateChunkMuffin;
	private JTextField textField_Sundae;
	private JTextField textField_VanillaCone;
	private JTextField textField_ApplePie;
	private JTextField textField_CaesarSalad;
	private JTextField textField_GardenFreshSalad;
	private JTextField textField_DoubleCheeseBurger;
	private JTextField textField_Addionalbeef;
	private JTextField textField_GrilledChickenBurger;
	private JTextField textField_AddionalChicken;
	private JCheckBox checkBox_DietCokeServed;
	private JCheckBox checkBox_CokeServed;
	private JCheckBox checkBox_OrangejuiceServed;
	private JCheckBox checkBox_ChickenNuggetServed;
	private JCheckBox checkBox_BananaChocolateChunkMuffinServed;
	private JCheckBox checkBox_SundaeServed;
	private JCheckBox checkBox_VanillaConeServed;
	private JCheckBox checkBox_ApplePieServed;
	private JCheckBox checkBox_CaesarSaladServed;
	private JCheckBox checkBox_GardenFreshSaladServed;
	private JCheckBox checkBox_DoubleCheeseBurgerServed;
	private JCheckBox checkBox_GrilledChickenBurgerServed;
	private JComboBox comboBox_DietCoke;
	private JComboBox comboBox_Coke;
	private JComboBox comboBox_Orangejuice;
	private JCheckBox checkBox_DietCoke;
	private JCheckBox checkBox_Coke;
	private JCheckBox checkBox_Orangejuice;
	private JComboBox comboBox_CaesarSalad;
	private JComboBox comboBox_GardenFreshSalad;
	private JTextField textField_TableNumber;
	private JTextField textField_Persons;
	private JSeparator separator_1;
	private JTextField textField_Discount;	

	public FoodPanel() {
		init();
		setServedCheckboxVisible(false);
	}

	public void enterNewOrderMode() {
		setServedCheckboxVisible(false);
		setNumberField(0);
		clearComboBoxAndChBox();
	}

	public void enterDisplayMode(Order order, boolean flag) {
		enterNewOrderMode();

		textField_Persons.setText(order.getPersons() + "");
		textField_TableNumber.setText(order.getTableNums());
		textField_Discount.setText(order.getDiscount()+"");

		Map<Drinks, Integer> drinkMap = order.getDrinkMap();
		Map<Snacks, Integer> snackMap = order.getSnackMap();
		Map<Desserts, Integer> dessertsMap = order.getDessertsMap();
		Map<Salads, Integer> saladsMap = order.getSaladsMap();
		Map<Burgers, Integer> burgersMap = order.getBurgersMap();

		for (Map.Entry<Drinks, Integer> entry : drinkMap.entrySet()) {
			Drinks drinks = entry.getKey();
			String name = drinks.getName();
			SizeTypeEnum sizeTypeEnum = drinks.getSizeTypeEnum();
			boolean withIce = drinks.isWithIce();
			int number = entry.getValue();

			if (name.equals(ConstUtils.DIET_COKE)) {
				fillDrinksFields(textField_DietCoke, comboBox_DietCoke,
						checkBox_DietCoke, number, sizeTypeEnum, withIce);
			} else if (name.equals(ConstUtils.COKE)) {
				fillDrinksFields(textField_Coke, comboBox_Coke, checkBox_Coke,
						number, sizeTypeEnum, withIce);
			} else if (name.equals(ConstUtils.ORANGE_JUICE)) {
				fillDrinksFields(textField_Orangejuice, comboBox_Orangejuice,
						checkBox_Orangejuice, number, sizeTypeEnum, withIce);
			}
		}

		for (Map.Entry<Snacks, Integer> entry : snackMap.entrySet()) {
			Snacks snacks = entry.getKey();
			String name = snacks.getName();
			int number = entry.getValue();
			if (name.equals(ConstUtils.CHICKEN_NUGGET)) {
				fillSnacksAndDessertsFields(textField_ChickenNugget, number);
			} else if (name.equals(ConstUtils.BANANA_CHOCOLATE_CHUNK_MUFFIN)) {
				fillSnacksAndDessertsFields(
						textField_BananaChocolateChunkMuffin, number);
			}
		}

		for (Map.Entry<Desserts, Integer> entry : dessertsMap.entrySet()) {
			Desserts desserts = entry.getKey();
			String name = desserts.getName();
			int number = entry.getValue();
			if (name.equals(ConstUtils.SUNDAE)) {
				fillSnacksAndDessertsFields(textField_Sundae, number);
			} else if (name.equals(ConstUtils.VANILLA_CONE)) {
				fillSnacksAndDessertsFields(textField_VanillaCone, number);
			} else if (name.equals(ConstUtils.APPLE_PIE)) {
				fillSnacksAndDessertsFields(textField_ApplePie, number);
			}
		}

		for (Map.Entry<Salads, Integer> entry : saladsMap.entrySet()) {
			Salads salad = entry.getKey();
			String name = salad.getName();
			SizeTypeEnum sizeTypeEnum = salad.getSizeTypeEnum();
			int number = entry.getValue();
			if (name.equals(ConstUtils.CAESAR_SALAD)) {
				fillSaladsFields(textField_CaesarSalad, comboBox_CaesarSalad,
						number, sizeTypeEnum);
			} else if (name.equals(ConstUtils.GARDEN_FRESH_SALAD)) {
				fillSaladsFields(textField_GardenFreshSalad,
						comboBox_GardenFreshSalad, number, sizeTypeEnum);
			}
		}

		for (Map.Entry<Burgers, Integer> entry : burgersMap.entrySet()) {
			Burgers burger = entry.getKey();
			String name = burger.getName();
			int number = entry.getValue();
			if (name.equals(ConstUtils.DOUBLE_CHEESEBURGER)) {
				int additionalNumber = burger
						.getAdditionalNumber(ConstUtils.INGREDIENT_BEEF);
				fillBurgerFields(textField_DoubleCheeseBurger,
						textField_Addionalbeef, number, additionalNumber);
			} else if (name.equals(ConstUtils.GRILLED_CHICKEN_BURGER)) {
				int additionalNumber = burger
						.getAdditionalNumber(ConstUtils.INGREDIENT_CHICKEN);
				fillBurgerFields(textField_GrilledChickenBurger,
						textField_AddionalChicken, number, additionalNumber);
			}
		}

		setServedCheckboxVisible(flag);
		setEditable(flag);
	}

	private void fillBurgerFields(JTextField numberField,
			JTextField additionalNumberField, int number, int additionalNumber) {
		numberField.setText(number + "");
		additionalNumberField.setText(additionalNumber + "");
	}

	private void fillSaladsFields(JTextField numberField, JComboBox combo,
			int number, SizeTypeEnum sizeTypeEnum) {
		numberField.setText(number + "");
		combo.setSelectedIndex(sizeTypeEnum.ordinal());
	}

	private void fillSnacksAndDessertsFields(JTextField numberField, int number) {
		numberField.setText(number + "");
	}

	private void fillDrinksFields(JTextField numberField, JComboBox combo,
			JCheckBox chbox, int number, SizeTypeEnum sizeTypeEnum,
			boolean withIce) {
		numberField.setText(number + "");
		chbox.setSelected(withIce);
		combo.setSelectedIndex(sizeTypeEnum.ordinal());
	}

	private void clearComboBoxAndChBox() {
		comboBox_CaesarSalad.setSelectedIndex(0);
		comboBox_Coke.setSelectedIndex(0);
		comboBox_DietCoke.setSelectedIndex(0);
		comboBox_Orangejuice.setSelectedIndex(0);
		comboBox_GardenFreshSalad.setSelectedIndex(0);
		checkBox_DietCoke.setSelected(false);
		checkBox_Coke.setSelected(false);
		checkBox_Orangejuice.setSelected(false);
	}

	public Order tryToMakeNewOrder() {
		int persons;
		try {
			persons = Integer.parseInt(textField_Persons.getText().trim());
			if (persons <= 0) {
				JOptionPane.showMessageDialog(null,
						"Please enter number of persons", "Tips",
						JOptionPane.INFORMATION_MESSAGE);
				return null;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Please enter number of persons!", "Tips",
					JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		double discount=1;
		try {
			discount = Double.parseDouble(textField_Discount.getText().trim());
			if(discount<0 || discount>1) {
				JOptionPane.showMessageDialog(null,
						"Please enter right form of discount", "Tips",
						JOptionPane.INFORMATION_MESSAGE);
				return null;				
			}
		}
		catch(NumberFormatException e) {
			discount = 1;
		}

		String tableNums = textField_TableNumber.getText().trim();
		if (tableNums.equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter # of table!",
					"Tips", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}

		Map<Drinks, Integer> drinkMap = new HashMap<Drinks, Integer>();
		Map<Snacks, Integer> snackMap = new HashMap<>();
		Map<Desserts, Integer> dessertsMap = new HashMap<>();
		Map<Salads, Integer> saladsMap = new HashMap<>();
		Map<Burgers, Integer> burgersMap = new HashMap<>();

		collectDrinks(drinkMap, textField_DietCoke, comboBox_DietCoke,
				checkBox_DietCoke, 0);
		collectDrinks(drinkMap, textField_Coke, comboBox_Coke, checkBox_Coke, 1);
		collectDrinks(drinkMap, textField_Orangejuice, comboBox_Orangejuice,
				checkBox_Orangejuice, 2);

		collectSnacks(snackMap, textField_ChickenNugget, 0);
		collectSnacks(snackMap, textField_BananaChocolateChunkMuffin, 1);

		collectDesserts(dessertsMap, textField_Sundae, 0);
		collectDesserts(dessertsMap, textField_VanillaCone, 1);
		collectDesserts(dessertsMap, textField_ApplePie, 2);

		collectSalads(saladsMap, textField_CaesarSalad, comboBox_CaesarSalad, 0);
		collectSalads(saladsMap, textField_GardenFreshSalad,
				comboBox_GardenFreshSalad, 1);

		collectBurgers(burgersMap, textField_DoubleCheeseBurger,
				textField_Addionalbeef, ConstUtils.INGREDIENT_BEEF, 0);
		collectBurgers(burgersMap, textField_GrilledChickenBurger,
				textField_AddionalChicken, ConstUtils.INGREDIENT_CHICKEN, 1);

		Map<String, Integer> needsMap = FuncUtils.getIngredientNeedsMap(
				drinkMap, snackMap, dessertsMap, saladsMap, burgersMap);

		if (MainSystem.getInstance().isEnough(needsMap)) {
			Order order = new Order(drinkMap, snackMap, dessertsMap, saladsMap,
					burgersMap, needsMap, persons, discount, tableNums);
			return order;
		} else {
			JOptionPane.showMessageDialog(null, "Not enough ingredients!",
					"Tips", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
	}

	private void collectBurgers(Map<Burgers, Integer> burgersMap,
			JTextField numberField, JTextField additionalNumberField,
			String additional, int type) {
		FoodFactory foodFactory = FoodFactory.getInstance();
		int number = FuncUtils.getNumberFromStr(numberField.getText().trim());
		if (number != 0) {
			int additionalNumber = FuncUtils
					.getNumberFromStr(additionalNumberField.getText().trim());
			Map<String, Integer> additionalMap = new HashMap<>();
			additionalMap.put(additional, additionalNumber);
			burgersMap
					.put(foodFactory.makeBurgers(additionalMap, type), number);
		}
	}

	private void collectSalads(Map<Salads, Integer> saladsMap,
			JTextField numberField, JComboBox combo, int type) {
		FoodFactory foodFactory = FoodFactory.getInstance();
		int number = FuncUtils.getNumberFromStr(numberField.getText().trim());
		if (number != 0) {
			SizeTypeEnum sizeTypeEnum = SizeTypeEnum.values()[combo
					.getSelectedIndex()];
			saladsMap.put(foodFactory.makeSalads(sizeTypeEnum, type), number);
		}
	}

	private void collectDesserts(Map<Desserts, Integer> dessertsMap,
			JTextField numberField, int type) {
		FoodFactory foodFactory = FoodFactory.getInstance();
		int number = FuncUtils.getNumberFromStr(numberField.getText().trim());
		if (number != 0) {
			dessertsMap.put(foodFactory.makeDesserts(type), number);
		}
	}

	private void collectSnacks(Map<Snacks, Integer> snackMap,
			JTextField numberField, int type) {
		FoodFactory foodFactory = FoodFactory.getInstance();
		int number = FuncUtils.getNumberFromStr(numberField.getText().trim());
		if (number != 0) {
			snackMap.put(foodFactory.makeSnacks(type), number);
		}
	}

	private void collectDrinks(Map<Drinks, Integer> drinkMap,
			JTextField numberField, JComboBox combo, JCheckBox chbox, int type) {
		FoodFactory foodFactory = FoodFactory.getInstance();
		int number = FuncUtils.getNumberFromStr(numberField.getText().trim());
		if (number != 0) {
			SizeTypeEnum sizeTypeEnum = SizeTypeEnum.values()[combo
					.getSelectedIndex()];
			boolean withIce = chbox.isSelected();
			drinkMap.put(foodFactory.makeDrinks(sizeTypeEnum, withIce, type),
					number);
		}
	}

	private void setNumberField(int value) {
		textField_DietCoke.setText(value + "");
		textField_Coke.setText(value + "");
		textField_Orangejuice.setText(value + "");
		textField_ChickenNugget.setText(value + "");
		textField_BananaChocolateChunkMuffin.setText(value + "");
		textField_Sundae.setText(value + "");
		textField_VanillaCone.setText(value + "");
		textField_ApplePie.setText(value + "");
		textField_CaesarSalad.setText(value + "");
		textField_GardenFreshSalad.setText(value + "");
		textField_DoubleCheeseBurger.setText(value + "");
		textField_Addionalbeef.setText(value + "");
		textField_GrilledChickenBurger.setText(value + "");
		textField_AddionalChicken.setText(value + "");
		textField_Persons.setText(value + "");
		textField_Discount.setText(1+"");
		textField_TableNumber.setText("");
	}

	private void setEditable(boolean flag) {
		textField_DietCoke.setEditable(flag);
		textField_Coke.setEditable(flag);
		textField_Orangejuice.setEditable(flag);
		textField_ChickenNugget.setEditable(flag);
		textField_BananaChocolateChunkMuffin.setEditable(flag);
		textField_Sundae.setEditable(flag);
		textField_VanillaCone.setEditable(flag);
		textField_ApplePie.setEditable(flag);
		textField_CaesarSalad.setEditable(flag);
		textField_GardenFreshSalad.setEditable(flag);
		textField_DoubleCheeseBurger.setEditable(flag);
		textField_Addionalbeef.setEditable(flag);
		textField_GrilledChickenBurger.setEditable(flag);
		textField_AddionalChicken.setEditable(flag);

		checkBox_DietCokeServed.setEnabled(flag);
		checkBox_CokeServed.setEnabled(flag);
		comboBox_DietCoke.setEditable(flag);
		comboBox_Coke.setEditable(flag);
		comboBox_Orangejuice.setEditable(flag);
		checkBox_DietCoke.setEnabled(flag);
		checkBox_Coke.setEnabled(flag);
		checkBox_Orangejuice.setEnabled(flag);
		comboBox_CaesarSalad.setEditable(flag);
		comboBox_GardenFreshSalad.setEditable(flag);

		textField_TableNumber.setEditable(flag);
		textField_Persons.setEditable(flag);
		textField_Discount.setEditable(flag);
	}

	private void setServedCheckboxVisible(boolean flag) {
		checkBox_DietCokeServed.setVisible(flag);
		checkBox_CokeServed.setVisible(flag);
		checkBox_OrangejuiceServed.setVisible(flag);
		checkBox_ChickenNuggetServed.setVisible(flag);
		checkBox_BananaChocolateChunkMuffinServed.setVisible(flag);
		checkBox_SundaeServed.setVisible(flag);
		checkBox_VanillaConeServed.setVisible(flag);
		checkBox_ApplePieServed.setVisible(flag);
		checkBox_CaesarSaladServed.setVisible(flag);
		checkBox_GardenFreshSaladServed.setVisible(flag);
		checkBox_DoubleCheeseBurgerServed.setVisible(flag);
		checkBox_GrilledChickenBurgerServed.setVisible(flag);

		checkBox_DietCokeServed.setSelected(flag);
		checkBox_CokeServed.setSelected(flag);
		checkBox_OrangejuiceServed.setSelected(flag);
		checkBox_ChickenNuggetServed.setSelected(flag);
		checkBox_BananaChocolateChunkMuffinServed.setSelected(flag);
		checkBox_SundaeServed.setSelected(flag);
		checkBox_VanillaConeServed.setSelected(flag);
		checkBox_ApplePieServed.setSelected(flag);
		checkBox_CaesarSaladServed.setSelected(flag);
		checkBox_GardenFreshSaladServed.setSelected(flag);
		checkBox_DoubleCheeseBurgerServed.setSelected(flag);
		checkBox_GrilledChickenBurgerServed.setSelected(flag);
	}

	private void init() {
		setBounds(91, 16, 758, 445);
		setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Drinks");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 10, 54, 15);
		add(lblNewLabel_2);

		JLabel lblDietCoke = new JLabel("DietCoke");
		lblDietCoke.setBounds(10, 35, 70, 15);
		add(lblDietCoke);

		textField_DietCoke = new JTextField();
		textField_DietCoke.setBounds(239, 32, 66, 21);
		add(textField_DietCoke);
		textField_DietCoke.setColumns(10);

		comboBox_DietCoke = new JComboBox();
		comboBox_DietCoke.setModel(new DefaultComboBoxModel(SizeTypeEnum
				.values()));
		comboBox_DietCoke.setBounds(109, 32, 70, 21);
		add(comboBox_DietCoke);

		checkBox_DietCoke = new JCheckBox("Ice");
		checkBox_DietCoke.setBounds(185, 31, 48, 23);
		add(checkBox_DietCoke);

		JLabel lblCoke = new JLabel("Coke");
		lblCoke.setBounds(10, 67, 70, 15);
		add(lblCoke);

		comboBox_Coke = new JComboBox();
		comboBox_Coke.setModel(new DefaultComboBoxModel(SizeTypeEnum.values()));
		comboBox_Coke.setBounds(109, 64, 70, 21);
		add(comboBox_Coke);

		checkBox_Coke = new JCheckBox("Ice");
		checkBox_Coke.setBounds(185, 63, 48, 23);
		add(checkBox_Coke);

		textField_Coke = new JTextField();
		textField_Coke.setColumns(10);
		textField_Coke.setBounds(239, 64, 66, 21);
		add(textField_Coke);

		JLabel lblOrangejuice = new JLabel("OrangeJuice");
		lblOrangejuice.setBounds(10, 98, 86, 15);
		add(lblOrangejuice);

		comboBox_Orangejuice = new JComboBox();
		comboBox_Orangejuice.setModel(new DefaultComboBoxModel(SizeTypeEnum
				.values()));
		comboBox_Orangejuice.setBounds(109, 95, 70, 21);
		add(comboBox_Orangejuice);

		checkBox_Orangejuice = new JCheckBox("Ice");
		checkBox_Orangejuice.setBounds(185, 94, 48, 23);
		add(checkBox_Orangejuice);

		textField_Orangejuice = new JTextField();
		textField_Orangejuice.setColumns(10);
		textField_Orangejuice.setBounds(239, 95, 66, 21);
		add(textField_Orangejuice);

		JLabel lblSnacks = new JLabel("Snacks");
		lblSnacks.setFont(new Font("宋体", Font.BOLD, 12));
		lblSnacks.setBounds(10, 137, 54, 15);
		add(lblSnacks);

		JLabel lblChickennugget = new JLabel("ChickenNugget");
		lblChickennugget.setBounds(10, 168, 86, 15);
		add(lblChickennugget);

		textField_ChickenNugget = new JTextField();
		textField_ChickenNugget.setColumns(10);
		textField_ChickenNugget.setBounds(239, 165, 66, 21);
		add(textField_ChickenNugget);

		JLabel lblBananachocolatechunkmuffin = new JLabel(
				"BananaChocolateChunkMuffin");
		lblBananachocolatechunkmuffin.setBounds(10, 199, 195, 15);
		add(lblBananachocolatechunkmuffin);

		textField_BananaChocolateChunkMuffin = new JTextField();
		textField_BananaChocolateChunkMuffin.setColumns(10);
		textField_BananaChocolateChunkMuffin.setBounds(239, 196, 66, 21);
		add(textField_BananaChocolateChunkMuffin);

		JLabel lblDesserts = new JLabel("Desserts");
		lblDesserts.setFont(new Font("宋体", Font.BOLD, 12));
		lblDesserts.setBounds(405, 10, 70, 15);
		add(lblDesserts);

		checkBox_DietCokeServed = new JCheckBox("Served");
		checkBox_DietCokeServed.setBounds(315, 31, 70, 23);
		add(checkBox_DietCokeServed);

		checkBox_CokeServed = new JCheckBox("Served");
		checkBox_CokeServed.setBounds(315, 63, 70, 23);
		add(checkBox_CokeServed);

		checkBox_OrangejuiceServed = new JCheckBox("Served");
		checkBox_OrangejuiceServed.setBounds(315, 94, 70, 23);
		add(checkBox_OrangejuiceServed);

		checkBox_ChickenNuggetServed = new JCheckBox("Served");
		checkBox_ChickenNuggetServed.setBounds(315, 164, 70, 23);
		add(checkBox_ChickenNuggetServed);

		checkBox_BananaChocolateChunkMuffinServed = new JCheckBox("Served");
		checkBox_BananaChocolateChunkMuffinServed.setBounds(315, 195, 70, 23);
		add(checkBox_BananaChocolateChunkMuffinServed);

		JLabel lblSundae = new JLabel("Sundae");
		lblSundae.setBounds(405, 39, 86, 15);
		add(lblSundae);

		textField_Sundae = new JTextField();
		textField_Sundae.setColumns(10);
		textField_Sundae.setBounds(606, 36, 66, 21);
		add(textField_Sundae);

		checkBox_SundaeServed = new JCheckBox("Served");
		checkBox_SundaeServed.setBounds(682, 35, 70, 23);
		add(checkBox_SundaeServed);

		JLabel lblVanillacone = new JLabel("VanillaCone");
		lblVanillacone.setBounds(405, 67, 86, 15);
		add(lblVanillacone);

		textField_VanillaCone = new JTextField();
		textField_VanillaCone.setColumns(10);
		textField_VanillaCone.setBounds(606, 64, 66, 21);
		add(textField_VanillaCone);

		checkBox_VanillaConeServed = new JCheckBox("Served");
		checkBox_VanillaConeServed.setBounds(682, 63, 70, 23);
		add(checkBox_VanillaConeServed);

		JLabel lblApplepie = new JLabel("ApplePie");
		lblApplepie.setBounds(405, 94, 86, 15);
		add(lblApplepie);

		textField_ApplePie = new JTextField();
		textField_ApplePie.setColumns(10);
		textField_ApplePie.setBounds(606, 91, 66, 21);
		add(textField_ApplePie);

		checkBox_ApplePieServed = new JCheckBox("Served");
		checkBox_ApplePieServed.setBounds(682, 90, 70, 23);
		add(checkBox_ApplePieServed);

		JLabel lblSalads = new JLabel("Salads");
		lblSalads.setFont(new Font("宋体", Font.BOLD, 12));
		lblSalads.setBounds(405, 137, 70, 15);
		add(lblSalads);

		JLabel lblCaesarsalad = new JLabel("CaesarSalad");
		lblCaesarsalad.setBounds(405, 172, 103, 15);
		add(lblCaesarsalad);

		comboBox_CaesarSalad = new JComboBox();
		comboBox_CaesarSalad.setModel(new DefaultComboBoxModel(SizeTypeEnum
				.values()));
		comboBox_CaesarSalad.setBounds(526, 168, 70, 21);
		add(comboBox_CaesarSalad);

		textField_CaesarSalad = new JTextField();
		textField_CaesarSalad.setColumns(10);
		textField_CaesarSalad.setBounds(606, 169, 66, 21);
		add(textField_CaesarSalad);

		checkBox_CaesarSaladServed = new JCheckBox("Served");
		checkBox_CaesarSaladServed.setBounds(682, 168, 70, 23);
		add(checkBox_CaesarSaladServed);

		JLabel lblGardenfreshsalad = new JLabel("GardenFreshSalad");
		lblGardenfreshsalad.setBounds(405, 200, 111, 15);
		add(lblGardenfreshsalad);

		comboBox_GardenFreshSalad = new JComboBox();
		comboBox_GardenFreshSalad.setModel(new DefaultComboBoxModel(
				SizeTypeEnum.values()));
		comboBox_GardenFreshSalad.setBounds(526, 196, 70, 21);
		add(comboBox_GardenFreshSalad);

		textField_GardenFreshSalad = new JTextField();
		textField_GardenFreshSalad.setColumns(10);
		textField_GardenFreshSalad.setBounds(606, 197, 66, 21);
		add(textField_GardenFreshSalad);

		checkBox_GardenFreshSaladServed = new JCheckBox("Served");
		checkBox_GardenFreshSaladServed.setBounds(682, 196, 70, 23);
		add(checkBox_GardenFreshSaladServed);

		JLabel lblBurgers = new JLabel("Burgers");
		lblBurgers.setFont(new Font("宋体", Font.BOLD, 12));
		lblBurgers.setBounds(10, 241, 54, 15);
		add(lblBurgers);

		JLabel lblDoublecheeseburger = new JLabel("DoubleCheeseBurger");
		lblDoublecheeseburger.setBounds(10, 266, 158, 15);
		add(lblDoublecheeseburger);

		textField_DoubleCheeseBurger = new JTextField();
		textField_DoubleCheeseBurger.setColumns(10);
		textField_DoubleCheeseBurger.setBounds(239, 263, 66, 21);
		add(textField_DoubleCheeseBurger);

		JLabel lblAddionalbeef = new JLabel("AdditionalBeef");
		lblAddionalbeef.setBounds(317, 266, 86, 15);
		add(lblAddionalbeef);

		textField_Addionalbeef = new JTextField();
		textField_Addionalbeef.setColumns(10);
		textField_Addionalbeef.setBounds(442, 262, 66, 21);
		add(textField_Addionalbeef);

		checkBox_DoubleCheeseBurgerServed = new JCheckBox("Served");
		checkBox_DoubleCheeseBurgerServed.setBounds(514, 262, 70, 23);
		add(checkBox_DoubleCheeseBurgerServed);

		JLabel lblGrilledchickenburger = new JLabel("GrilledChickenBurger");
		lblGrilledchickenburger.setBounds(10, 294, 158, 15);
		add(lblGrilledchickenburger);

		textField_GrilledChickenBurger = new JTextField();
		textField_GrilledChickenBurger.setColumns(10);
		textField_GrilledChickenBurger.setBounds(239, 291, 66, 21);
		add(textField_GrilledChickenBurger);

		JLabel lblAddionalchicken = new JLabel("AdditionalChicken");
		lblAddionalchicken.setBounds(317, 294, 115, 15);
		add(lblAddionalchicken);

		textField_AddionalChicken = new JTextField();
		textField_AddionalChicken.setColumns(10);
		textField_AddionalChicken.setBounds(442, 290, 66, 21);
		add(textField_AddionalChicken);

		checkBox_GrilledChickenBurgerServed = new JCheckBox("Served");
		checkBox_GrilledChickenBurgerServed.setBounds(514, 290, 70, 23);
		add(checkBox_GrilledChickenBurgerServed);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 319, 738, 15);
		add(separator);

		JLabel lblTablenumber = new JLabel("# TableNumber");
		lblTablenumber.setBounds(10, 334, 158, 15);
		add(lblTablenumber);

		textField_TableNumber = new JTextField();
		textField_TableNumber.setColumns(10);
		textField_TableNumber.setBounds(239, 331, 66, 21);
		add(textField_TableNumber);

		JLabel lblPersons = new JLabel("Persons");
		lblPersons.setBounds(10, 367, 70, 15);
		add(lblPersons);

		textField_Persons = new JTextField();
		textField_Persons.setColumns(10);
		textField_Persons.setBounds(239, 364, 66, 21);
		add(textField_Persons);
		
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(317, 367, 70, 15);
		add(lblDiscount);
		
		textField_Discount = new JTextField();
		textField_Discount.setColumns(10);
		textField_Discount.setBounds(442, 364, 66, 21);
		add(textField_Discount);		

		separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(10, 392, 738, 15);
		add(separator_1);
	}

	public void checkOrder(Order order) {
		LogUtils.getInstance().log(
				"Order " + order.getOrderID() + " checking starts");
		if (!checkBox_DietCokeServed.isSelected()) {
			order.removeDrinks(ConstUtils.DIET_COKE);
		}
		if (!checkBox_CokeServed.isSelected()) {
			order.removeDrinks(ConstUtils.COKE);
		}
		if (!checkBox_OrangejuiceServed.isSelected()) {
			order.removeDrinks(ConstUtils.ORANGE_JUICE);
		}
		if (!checkBox_ChickenNuggetServed.isSelected()) {
			order.removeSnacks(ConstUtils.CHICKEN_NUGGET);
		}
		if (!checkBox_BananaChocolateChunkMuffinServed.isSelected()) {
			order.removeSnacks(ConstUtils.BANANA_CHOCOLATE_CHUNK_MUFFIN);
		}
		if (!checkBox_SundaeServed.isSelected()) {
			order.removeDesserts(ConstUtils.SUNDAE);
		}
		if (!checkBox_VanillaConeServed.isSelected()) {
			order.removeDesserts(ConstUtils.VANILLA_CONE);
		}
		if (!checkBox_ApplePieServed.isSelected()) {
			order.removeDesserts(ConstUtils.APPLE_PIE);
		}
		if (!checkBox_CaesarSaladServed.isSelected()) {
			order.removeSalads(ConstUtils.CAESAR_SALAD);
		}
		if (!checkBox_GardenFreshSaladServed.isSelected()) {
			order.removeSalads(ConstUtils.GARDEN_FRESH_SALAD);
		}
		if (!checkBox_DoubleCheeseBurgerServed.isSelected()) {
			order.removeBurgers(ConstUtils.DOUBLE_CHEESEBURGER);
		}
		if (!checkBox_GrilledChickenBurgerServed.isSelected()) {
			order.removeBurgers(ConstUtils.GRILLED_CHICKEN_BURGER);
		}
		LogUtils.getInstance().log(
				"Order " + order.getOrderID() + " is finished checking");
	}
}
