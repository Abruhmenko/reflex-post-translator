/**
 * generated by Xtext 2.25.0
 */
package ru.iaie.reflexold.reflexOld.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import ru.iaie.reflexold.reflexOld.ProgramVariable;
import ru.iaie.reflexold.reflexOld.ReflexOldPackage;
import ru.iaie.reflexold.reflexOld.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Program Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.ProgramVariableImpl#isLocal <em>Local</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.ProgramVariableImpl#isShared <em>Shared</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.ProgramVariableImpl#getProcesses <em>Processes</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.ProgramVariableImpl#getType <em>Type</em>}</li>
 *   <li>{@link ru.iaie.reflexold.reflexOld.impl.ProgramVariableImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProgramVariableImpl extends ProcessVariableImpl implements ProgramVariable
{
  /**
   * The default value of the '{@link #isLocal() <em>Local</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLocal()
   * @generated
   * @ordered
   */
  protected static final boolean LOCAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isLocal() <em>Local</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLocal()
   * @generated
   * @ordered
   */
  protected boolean local = LOCAL_EDEFAULT;

  /**
   * The default value of the '{@link #isShared() <em>Shared</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isShared()
   * @generated
   * @ordered
   */
  protected static final boolean SHARED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isShared() <em>Shared</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isShared()
   * @generated
   * @ordered
   */
  protected boolean shared = SHARED_EDEFAULT;

  /**
   * The cached value of the '{@link #getProcesses() <em>Processes</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProcesses()
   * @generated
   * @ordered
   */
  protected EList<ru.iaie.reflexold.reflexOld.Process> processes;

  /**
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final Type TYPE_EDEFAULT = Type.VOID_EN;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected Type type = TYPE_EDEFAULT;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProgramVariableImpl()
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
    return ReflexOldPackage.Literals.PROGRAM_VARIABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isLocal()
  {
    return local;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setLocal(boolean newLocal)
  {
    boolean oldLocal = local;
    local = newLocal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.PROGRAM_VARIABLE__LOCAL, oldLocal, local));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isShared()
  {
    return shared;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setShared(boolean newShared)
  {
    boolean oldShared = shared;
    shared = newShared;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.PROGRAM_VARIABLE__SHARED, oldShared, shared));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<ru.iaie.reflexold.reflexOld.Process> getProcesses()
  {
    if (processes == null)
    {
      processes = new EObjectResolvingEList<ru.iaie.reflexold.reflexOld.Process>(ru.iaie.reflexold.reflexOld.Process.class, this, ReflexOldPackage.PROGRAM_VARIABLE__PROCESSES);
    }
    return processes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Type getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setType(Type newType)
  {
    Type oldType = type;
    type = newType == null ? TYPE_EDEFAULT : newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.PROGRAM_VARIABLE__TYPE, oldType, type));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ReflexOldPackage.PROGRAM_VARIABLE__NAME, oldName, name));
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
      case ReflexOldPackage.PROGRAM_VARIABLE__LOCAL:
        return isLocal();
      case ReflexOldPackage.PROGRAM_VARIABLE__SHARED:
        return isShared();
      case ReflexOldPackage.PROGRAM_VARIABLE__PROCESSES:
        return getProcesses();
      case ReflexOldPackage.PROGRAM_VARIABLE__TYPE:
        return getType();
      case ReflexOldPackage.PROGRAM_VARIABLE__NAME:
        return getName();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ReflexOldPackage.PROGRAM_VARIABLE__LOCAL:
        setLocal((Boolean)newValue);
        return;
      case ReflexOldPackage.PROGRAM_VARIABLE__SHARED:
        setShared((Boolean)newValue);
        return;
      case ReflexOldPackage.PROGRAM_VARIABLE__PROCESSES:
        getProcesses().clear();
        getProcesses().addAll((Collection<? extends ru.iaie.reflexold.reflexOld.Process>)newValue);
        return;
      case ReflexOldPackage.PROGRAM_VARIABLE__TYPE:
        setType((Type)newValue);
        return;
      case ReflexOldPackage.PROGRAM_VARIABLE__NAME:
        setName((String)newValue);
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
      case ReflexOldPackage.PROGRAM_VARIABLE__LOCAL:
        setLocal(LOCAL_EDEFAULT);
        return;
      case ReflexOldPackage.PROGRAM_VARIABLE__SHARED:
        setShared(SHARED_EDEFAULT);
        return;
      case ReflexOldPackage.PROGRAM_VARIABLE__PROCESSES:
        getProcesses().clear();
        return;
      case ReflexOldPackage.PROGRAM_VARIABLE__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case ReflexOldPackage.PROGRAM_VARIABLE__NAME:
        setName(NAME_EDEFAULT);
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
      case ReflexOldPackage.PROGRAM_VARIABLE__LOCAL:
        return local != LOCAL_EDEFAULT;
      case ReflexOldPackage.PROGRAM_VARIABLE__SHARED:
        return shared != SHARED_EDEFAULT;
      case ReflexOldPackage.PROGRAM_VARIABLE__PROCESSES:
        return processes != null && !processes.isEmpty();
      case ReflexOldPackage.PROGRAM_VARIABLE__TYPE:
        return type != TYPE_EDEFAULT;
      case ReflexOldPackage.PROGRAM_VARIABLE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
    result.append(" (local: ");
    result.append(local);
    result.append(", shared: ");
    result.append(shared);
    result.append(", type: ");
    result.append(type);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //ProgramVariableImpl
