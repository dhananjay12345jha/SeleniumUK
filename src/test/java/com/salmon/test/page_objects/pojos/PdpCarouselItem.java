package com.salmon.test.page_objects.pojos;

import com.salmon.test.utils.SeleniumUtils;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@Data
public class PdpCarouselItem
{
	private static final By HEART_LOCATOR = By.className("product-item__saveditem-icon");
	private static final By IMAGE_LOCATOR = By.className("carousel-component__img");
	private static final By NAME_LOCATOR = By.className("product-item__name");
	private static final By PRICE_LOCATOR = By.className("carousel-component-details__price");
	private static final By PREVIOUS_PRICE_LOCATOR = By.className("product-item__price--previous-price");
	private static final By VARIANT_SELECTOR_LOCATOR = By.className("carousel-component-details__select");
	private static final By ONE_SIZE_LOCATOR = By.className("carousel-component-details__one-size");
	private static final By ADD_TO_BAG_LOCATOR = By.className("carousel-component-details__bag-button");

	private final WebElement heart;
	private final WebElement image;
	private final WebElement name;
	private final WebElement price;
	private final WebElement previousPrice;

	private final WebElement variantSelector;
	private final WebElement oneSizeMessage;

	private final WebElement addToBagButton;

//	public PdpCarouselItem(final WebElement heart, final WebElement image, final WebElement name, final WebElement price, final WebElement previousPrice, final WebElement variantSelector, final WebElement oneSizeMessage, final WebElement addToBagButton)
//	{
//		this.heart = heart;
//		this.image = image;
//		this.name = name;
//		this.price = price;
//		this.previousPrice = previousPrice;
//		this.variantSelector = variantSelector;
//		this.oneSizeMessage = oneSizeMessage;
//		this.addToBagButton = addToBagButton;
//	}

	public static PdpCarouselItem generatePdpCarouselItem(final WebElement element)
	{
		final WebElement heart = element.findElement(HEART_LOCATOR);
		final WebElement image = element.findElement(IMAGE_LOCATOR);
		final WebElement name = element.findElement(NAME_LOCATOR);
		final WebElement price = element.findElement(PRICE_LOCATOR);
		final WebElement previousPrice = SeleniumUtils.findElementOrElseNull(element, PREVIOUS_PRICE_LOCATOR);

		final WebElement variantSelector = SeleniumUtils.findElementOrElseNull(element, VARIANT_SELECTOR_LOCATOR);
		final WebElement oneSizeMessage = SeleniumUtils.findElementOrElseNull(element, ONE_SIZE_LOCATOR);

		final WebElement addToBagButton = element.findElement(ADD_TO_BAG_LOCATOR);

		return new PdpCarouselItem(heart,
								   image,
								   name,
								   price,
								   previousPrice,
								   variantSelector,
								   oneSizeMessage,
								   addToBagButton);
	}

	public String getSelectedVariant()
	{
		return new Select(variantSelector).getFirstSelectedOption().getText();
	}
}
