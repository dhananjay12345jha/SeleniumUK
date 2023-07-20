package com.salmon.test.page_objects.pojos;

import com.salmon.test.utils.SeleniumUtils;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Data
public class FavouriteItem
{
	private static final By IMAGE_LOCATOR = By.className("product-item__carousel");
	private static final By NAME_LOCATOR = By.className("product-item__name");
	private static final By PRICE_LOCATOR = By.className("saveditems__price");
	private static final By VARIANT_DETAILS_LOCATOR = By.className("saveditems__size-colour");
	private static final By MOVE_TO_BAG_LOCATOR = By.className("saveditems__btn-action");
	private static final By SIZE_SELECTOR_LOCATOR = By.className("saveditems--select");
	private static final By CHANGE_SIZE_LOCATOR = By.className("saveditems__change-size");

	private final WebElement image;
	private final WebElement name;
	private final WebElement price;
	private final WebElement variantDetails;

	private final WebElement moveToBagButton;
	private final WebElement sizeSelector;
	private final WebElement changeSizeButton;

//	public FavouriteItem(final WebElement image, final WebElement name, final WebElement price, final WebElement variantDetails, final WebElement moveToBagButton, final WebElement sizeSelector, final WebElement changeSizeButton)
//	{
//		this.image = image;
//		this.name = name;
//		this.price = price;
//		this.variantDetails = variantDetails;
//		this.moveToBagButton = moveToBagButton;
//		this.sizeSelector = sizeSelector;
//		this.changeSizeButton = changeSizeButton;
//	}

	public static FavouriteItem generateFavouriteItem(final WebElement element)
	{
		final WebElement image = element.findElement(IMAGE_LOCATOR);
		final WebElement name = element.findElement(NAME_LOCATOR);
		final WebElement price = element.findElement(PRICE_LOCATOR);
		final WebElement variantDetails = element.findElement(VARIANT_DETAILS_LOCATOR);

		final WebElement moveToBagButton = SeleniumUtils.findElementOrElseNull(element, MOVE_TO_BAG_LOCATOR);
		final WebElement sizeSelector = SeleniumUtils.findElementOrElseNull(element, SIZE_SELECTOR_LOCATOR);
		final WebElement changeSizeButton = SeleniumUtils.findElementOrElseNull(element, CHANGE_SIZE_LOCATOR);

		return new FavouriteItem(image,
								 name,
								 price,
								 variantDetails,
								 moveToBagButton,
								 sizeSelector,
								 changeSizeButton);
	}
}
