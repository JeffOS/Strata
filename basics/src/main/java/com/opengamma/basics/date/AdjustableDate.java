/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.basics.date;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * An adjustable date.
 * <p>
 * This class combines an unadjusted date and the business day adjustment necessary to adjust it.
 * Calling the {@link #adjusted()} method will return the adjusted date.
 * 
 * <h4>Usage</h4>
 * {@code AdjustableDate} contains enough information to directly return the adjusted date:
 * <pre>
 *  LocalDate adjusted = adjustableDate.adjusted();
 * </pre>
 */
@BeanDefinition(builderScope = "private")
public final class AdjustableDate
    implements ImmutableBean, Serializable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The unadjusted date.
   * <p>
   * This date may be a non-business day.
   * The business day adjustment is used to ensure it is a valid business day.
   */
  @PropertyDefinition(validate = "notNull")
  private final LocalDate unadjusted;
  /**
   * The business day adjustment that is to be applied to the unadjusted date.
   * <p>
   * This is used to adjust the date if it is not a business day.
   */
  @PropertyDefinition(validate = "notNull")
  private final BusinessDayAdjustment adjustment;

  //-------------------------------------------------------------------------
  /**
   * Obtains an instance with no business day adjustment.
   * <p>
   * This creates an adjustable date from the specified date.
   * No business day adjustment applies, thus the result of {@link #adjusted()}
   * is the specified date.
   * 
   * @param date  the date
   * @return the adjustable date
   */
  public static AdjustableDate of(LocalDate date) {
    return new AdjustableDate(date, BusinessDayAdjustment.NONE);
  }

  /**
   * Obtains an adjustable date.
   * <p>
   * This creates an adjustable date from the unadjusted date and business day adjustment.
   * The adjusted date is accessible via {@link #adjusted()}.
   * 
   * @param unadjusted  the unadjusted date
   * @param adjustment  the business day adjustment to apply to the unadjusted date
   * @return the adjustable date
   */
  public static AdjustableDate of(LocalDate unadjusted, BusinessDayAdjustment adjustment) {
    return new AdjustableDate(unadjusted, adjustment);
  }

  //-------------------------------------------------------------------------
  /**
   * Adjusts the date using the business day adjustment.
   * <p>
   * This returns the adjusted date, calculated by applying the business day
   * adjustment to the unadjusted date.
   * 
   * @return the adjusted date
   */
  public LocalDate adjusted() {
    return adjustment.adjust(unadjusted);
  }

  //-------------------------------------------------------------------------
  /**
   * Returns a string describing the adjustable date.
   * 
   * @return the descriptive string
   */
  @Override
  public String toString() {
    if (adjustment.equals(BusinessDayAdjustment.NONE)) {
      return unadjusted.toString();
    }
    return new StringBuilder(64)
        .append(unadjusted)
        .append(" adjusted by ")
        .append(adjustment).toString();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AdjustableDate}.
   * @return the meta-bean, not null
   */
  public static AdjustableDate.Meta meta() {
    return AdjustableDate.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(AdjustableDate.Meta.INSTANCE);
  }

  private AdjustableDate(
      LocalDate unadjusted,
      BusinessDayAdjustment adjustment) {
    JodaBeanUtils.notNull(unadjusted, "unadjusted");
    JodaBeanUtils.notNull(adjustment, "adjustment");
    this.unadjusted = unadjusted;
    this.adjustment = adjustment;
  }

  @Override
  public AdjustableDate.Meta metaBean() {
    return AdjustableDate.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unadjusted date.
   * <p>
   * This date may be a non-business day.
   * The business day adjustment is used to ensure it is a valid business day.
   * @return the value of the property, not null
   */
  public LocalDate getUnadjusted() {
    return unadjusted;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the business day adjustment that is to be applied to the unadjusted date.
   * <p>
   * This is used to adjust the date if it is not a business day.
   * @return the value of the property, not null
   */
  public BusinessDayAdjustment getAdjustment() {
    return adjustment;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      AdjustableDate other = (AdjustableDate) obj;
      return JodaBeanUtils.equal(getUnadjusted(), other.getUnadjusted()) &&
          JodaBeanUtils.equal(getAdjustment(), other.getAdjustment());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getUnadjusted());
    hash += hash * 31 + JodaBeanUtils.hashCode(getAdjustment());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AdjustableDate}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code unadjusted} property.
     */
    private final MetaProperty<LocalDate> unadjusted = DirectMetaProperty.ofImmutable(
        this, "unadjusted", AdjustableDate.class, LocalDate.class);
    /**
     * The meta-property for the {@code adjustment} property.
     */
    private final MetaProperty<BusinessDayAdjustment> adjustment = DirectMetaProperty.ofImmutable(
        this, "adjustment", AdjustableDate.class, BusinessDayAdjustment.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "unadjusted",
        "adjustment");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 482476551:  // unadjusted
          return unadjusted;
        case 1977085293:  // adjustment
          return adjustment;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends AdjustableDate> builder() {
      return new AdjustableDate.Builder();
    }

    @Override
    public Class<? extends AdjustableDate> beanType() {
      return AdjustableDate.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code unadjusted} property.
     * @return the meta-property, not null
     */
    public MetaProperty<LocalDate> unadjusted() {
      return unadjusted;
    }

    /**
     * The meta-property for the {@code adjustment} property.
     * @return the meta-property, not null
     */
    public MetaProperty<BusinessDayAdjustment> adjustment() {
      return adjustment;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 482476551:  // unadjusted
          return ((AdjustableDate) bean).getUnadjusted();
        case 1977085293:  // adjustment
          return ((AdjustableDate) bean).getAdjustment();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code AdjustableDate}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<AdjustableDate> {

    private LocalDate unadjusted;
    private BusinessDayAdjustment adjustment;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 482476551:  // unadjusted
          return unadjusted;
        case 1977085293:  // adjustment
          return adjustment;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 482476551:  // unadjusted
          this.unadjusted = (LocalDate) newValue;
          break;
        case 1977085293:  // adjustment
          this.adjustment = (BusinessDayAdjustment) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public AdjustableDate build() {
      return new AdjustableDate(
          unadjusted,
          adjustment);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("AdjustableDate.Builder{");
      buf.append("unadjusted").append('=').append(JodaBeanUtils.toString(unadjusted)).append(',').append(' ');
      buf.append("adjustment").append('=').append(JodaBeanUtils.toString(adjustment));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
