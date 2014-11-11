package de.greenrobot.dao;

public enum IdentityScopeType
{
  static
  {
    None = new IdentityScopeType("None", 1);
    IdentityScopeType[] arrayOfIdentityScopeType = new IdentityScopeType[2];
    arrayOfIdentityScopeType[0] = Session;
    arrayOfIdentityScopeType[1] = None;
    ENUM$VALUES = arrayOfIdentityScopeType;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.IdentityScopeType
 * JD-Core Version:    0.6.0
 */