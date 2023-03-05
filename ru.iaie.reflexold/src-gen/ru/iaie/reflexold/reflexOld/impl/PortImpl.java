/**
 * generated by Xtext 2.25.0
 */
package ru.iaie.reflexold.reflexOld.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import ru.iaie.reflexold.reflexOld.Port;
import ru.iaie.reflexold.reflexOld.PortType;
import ru.iaie.reflexold.reflexOld.ReflexOldPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.PortImpl#getType <em>Type</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.PortImpl#getName <em>Name</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.PortImpl#getAddr1 <em>Addr1</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.PortImpl#getAddr2 <em>Addr2</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.PortImpl#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PortImpl extends MinimalEObjectImpl.Container implements Port
{
  /**
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final PortType TYPE_EDEFAULT = PortType.INPUT_EN;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected PortType type = TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getAddr1() <em>Addr1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddr1()
   * @generated
   * @ordered
   */
  protected static final String ADDR1_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAddr1() <em>Addr1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddr1()
   * @generated
   * @ordered
   */
  protected String addr1 = ADDR1_EDEFAULT;

  /**
   * The default value of the '{@link #getAddr2() <em>Addr2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddr2()
   * @generated
   * @ordered
   */
  protected static final String ADDR2_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAddr2() <em>Addr2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddr2()
   * @generated
   * @ordered
   */
  protected String addr2 = ADDR2_EDEFAULT;

  /**
   * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSize()
   * @generated
   * @ordered
   */
  protected static final String SIZE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSize()
   * @generated
   * @ordered
   */
  protected String size = SIZE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PortImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ReflexOldPackage.Literals.PORT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public PortType getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setType(PortType newType)
  {
    PortType oldType = type;
    type = newType == null ? TYPE_EDEFAULT : newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.PORT__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.PORT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getAddr1()
  {
    return addr1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setAddr1(String newAddr1)
  {
    String oldAddr1 = addr1;
    addr1 = newAddr1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.PORT__ADDR1, oldAddr1, addr1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getAddr2()
  {
    return addr2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setAddr2(String newAddr2)
  {
    String oldAddr2 = addr2;
    addr2 = newAddr2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.PORT__ADDR2, oldAddr2, addr2));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getSize()
  {
    return size;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setSize(String newSize)
  {
    String oldSize = size;
    size = newSize;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.PORT__SIZE, oldSize, size));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ReflexOldPackage.PORT__TYPE:
        return getType();
      case ReflexOldPackage.PORT__NAME:
        return getName();
      case ReflexOldPackage.PORT__ADDR1:
        return getAddr1();
      case ReflexOldPackage.PORT__ADDR2:
        return getAddr2();
      case ReflexOldPackage.PORT__SIZE:
        return getSize();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ReflexOldPackage.PORT__TYPE:
        setType((PortType)newValue);
        return;
      case ReflexOldPackage.PORT__NAME:
        setName((String)newValue);
        return;
      case ReflexOldPackage.PORT__ADDR1:
        setAddr1((String)newValue);
        return;
      case ReflexOldPackage.PORT__ADDR2:
        setAddr2((String)newValue);
        return;
      case ReflexOldPackage.PORT__SIZE:
        setSize((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ReflexOldPackage.PORT__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case ReflexOldPackage.PORT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ReflexOldPackage.PORT__ADDR1:
        setAddr1(ADDR1_EDEFAULT);
        return;
      case ReflexOldPackage.PORT__ADDR2:
        setAddr2(ADDR2_EDEFAULT);
        return;
      case ReflexOldPackage.PORT__SIZE:
        setSize(SIZE_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ReflexOldPackage.PORT__TYPE:
        return type != TYPE_EDEFAULT;
      case ReflexOldPackage.PORT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ReflexOldPackage.PORT__ADDR1:
        return ADDR1_EDEFAULT == null ? addr1 != null : !ADDR1_EDEFAULT.equals(addr1);
      case ReflexOldPackage.PORT__ADDR2:
        return ADDR2_EDEFAULT == null ? addr2 != null : !ADDR2_EDEFAULT.equals(addr2);
      case ReflexOldPackage.PORT__SIZE:
        return SIZE_EDEFAULT == null ? size != null : !SIZE_EDEFAULT.equals(size);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (type: ");
    result.append(type);
    result.append(", name: ");
    result.append(name);
    result.append(", addr1: ");
    result.append(addr1);
    result.append(", addr2: ");
    result.append(addr2);
    result.append(", size: ");
    result.append(size);
    result.append(')');
    return result.toString();
  }

} //PortImpl
