package models.piece.type;

public enum CharacterType
{
    WECHAT(0),
    FACEBOOK(1),
    GOOGLE(2),
    WEIBO(3),
    TWITTER(4),
    BAIDU(5);

    private int characterType;

    CharacterType(int characterType)
    {
        this.characterType = characterType;
    }

    public int getCharacterTypeId()
    {
        return this.characterType;
    }
}
