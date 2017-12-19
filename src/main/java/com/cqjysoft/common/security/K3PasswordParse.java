package com.cqjysoft.common.security;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("serial")
public class K3PasswordParse {
	
	private static HashMap<String,String[]> map = new HashMap<String,String[]>(){{
		put("0",new String[]{" P ","#  ",",  ","P ","#  ",",  "});
		put("1",new String[]{" Q ","#$ ",",0 ","Q ","#$ ",",0 "});
		put("2",new String[]{" R ","#( ",",@ ","R ","#( ",",@ "});
		put("3",new String[]{" S ","#, ",",P ","S ","#, ",",P "});
		put("4",new String[]{" T ","#0 ","-  ","T ","#0 ","-  "});
		put("5",new String[]{" U ","#4 ","-0 ","U ","#4 ","-0 "});
		put("6",new String[]{" V ","#8 ","-@ ","V ","#8 ","-@ "});
		put("7",new String[]{" W ","#< ","-P ","W ","#< ","-P "});
		put("8",new String[]{" X ","#@ ",".  ","X ","#@ ",".  "});
		put("9",new String[]{" Y ","#D ",".0 ","Y ","#D ",".0 "});
		put("a",new String[]{"!A ","&$ ","80","!A ","&$ ","80"});
		put("b",new String[]{"!B ","&( ","8@","!B ","&( ","8@"});
		put("c",new String[]{"!C ","&, ","8P","!C ","&, ","8P"});
		put("d",new String[]{"!D ","&0 ","9 ","!D ","&0 ","9 "});
		put("e",new String[]{"!E ","&4 ","90","!E ","&4 ","90"});
		put("f",new String[]{"!F ","&8 ","9@","!F ","&8 ","9@"});
		put("g",new String[]{"!G ","&< ","9P","!G ","&< ","9P"});
		put("h",new String[]{"!H ","&@ ",": ","!H ","&@ ",": "});
		put("i",new String[]{"!I ","&D ",":0","!I ","&D ",":0"});
		put("j",new String[]{"!J ","&H ",":@","!J ","&H ",":@"});
		put("k",new String[]{"!K ","&L ",":P","!K ","&L ",":P"});
		put("l",new String[]{"!L ","&P ","; ","!L ","&P ","; "});
		put("m",new String[]{"!M ","&T ",";0","!M ","&T ",";0"});
		put("n",new String[]{"!N ","&X ",";@","!N ","&X ",";@"});
		put("o",new String[]{"!O ","&\\ ",";P","!O ","&\\ ",";P"});
		put("p",new String[]{"!P ","\'  ","< ","!P ","\'  ","< "});
		put("q",new String[]{"!Q ","\'$ ","<0","!Q ","\'$ ","<0"});
		put("r",new String[]{"!R ","\'( ","<@","!R ","\'( ","<@"});
		put("s",new String[]{"!S ","\', ","<P","!S ","\', ","<P"});
		put("t",new String[]{"!T ","\'0 ","= ","!T ","\'0 ","= "});
		put("u",new String[]{"!U ","\'4 ","=0","!U ","\'4 ","=0"});
		put("v",new String[]{"!V ","\'8 ","=@","!V ","\'8 ","=@"});
		put("w",new String[]{"!W ","\'< ","=P","!W ","\'< ","=P"});
		put("x",new String[]{"!X ","\'@ ","> ","!X ","\'@ ","> "});
		put("y",new String[]{"!Y ","\'D ",">0","!Y ","\'D ",">0"});
		put("z",new String[]{"!Z ","\'H ",">@","!Z ","\'H ",">@"});
		put("A",new String[]{"!! ","$$ ","00","!! ","$$ ","00"});
		put("B",new String[]{"!\" ","$( ","0@","!\" ","$( ","0@"});
		put("C",new String[]{"!# ","$, ","0P","!# ","$, ","0P"});
		put("D",new String[]{"!$ ","$0 ","1 ","!$ ","$0 ","1 "});
		put("E",new String[]{"!% ","$4 ","10","!% ","$4 ","10"});
		put("F",new String[]{"!& ","$8 ","1@","!& ","$8 ","1@"});
		put("G",new String[]{"!\' ","$< ","1P","!\' ","$< ","1P"});
		put("H",new String[]{"!( ","$@ ","2 ","!( ","$@ ","2 "});
		put("I",new String[]{"!) ","$D ","20","!) ","$D ","20"});
		put("J",new String[]{"!* ","$H ","2@","!* ","$H ","2@"});
		put("K",new String[]{"!+ ","$L ","2P","!+ ","$L ","2P"});
		put("L",new String[]{"!, ","$P ","3 ","!, ","$P ","3 "});
		put("M",new String[]{"!- ","$T ","30","!- ","$T ","30"});
		put("N",new String[]{"!. ","$X ","3@","!. ","$X ","3@"});
		put("O",new String[]{"!/ ","$\\ ","3P","!/ ","$\\ ","3P"});
		put("P",new String[]{"!0 ","%  ","4 ","!0 ","%  ","4 "});
		put("Q",new String[]{"!1 ","%$ ","40","!1 ","%$ ","40"});
		put("R",new String[]{"!2 ","%( ","4@","!2 ","%( ","4@"});
		put("S",new String[]{"!3 ","%, ","4P","!3 ","%, ","4P"});
		put("T",new String[]{"!4 ","%0 ","5 ","!4 ","%0 ","5 "});
		put("U",new String[]{"!5 ","%4 ","50","!5 ","%4 ","50"});
		put("V",new String[]{"!6 ","%8 ","5@","!6 ","%8 ","5@"});
		put("W",new String[]{"!7 ","%< ","5P","!7 ","%< ","5P"});
		put("X",new String[]{"!8 ","%@ ","6 ","!8 ","%@ ","6 "});
		put("Y",new String[]{"!9 ","%D ","60","!9 ","%D ","60"});
		put("Z",new String[]{"!: ","%H ","6@","!: ","%H ","6@"});
		put("!",new String[]{" A ","\"$ ","(0"," A ","\"$ ","(0"});
		put("@",new String[]{" ! ","$  ","0 "," ! ","$  ","0 "});
		put("#",new String[]{" C ","\", ","(P"," C ","\", ","(P"});
		put("$",new String[]{" D ","\"0 ",") "," D ","\"0 ",") "});
		put("%",new String[]{" E ","\"4 ",")0"," E ","\"4 ",")0"});
		put("^",new String[]{"!> ","%X ","7@","!> ","%X ","7@"});
		put("&",new String[]{" F ","\"8 ",")@"," F ","\"8 ",")@"});
		put("*",new String[]{" J ","\"H ","*@"," J ","\"H ","*@"});
		put("(",new String[]{" H ","\"@ ","* "," H ","\"@ ","* "});
		put(")",new String[]{" I ","\"D ","*0"," I ","\"D ","*0"});
		put("_",new String[]{"!? ","%\\ ","7P","!? ","%\\ ","7P"});
		put("+",new String[]{" K ","\"L ","*P"," K ","\"L ","*P"});
		put("=",new String[]{" ] ","#T ","/0"," ] ","#T ","/0"});
		put("-",new String[]{" M ","\"T ","+0"," M ","\"T ","+0"});
		put("[",new String[]{"!; ","%L ","6P","!; ","%L ","6P"});
		put("]",new String[]{"!= ","%T ","70","!= ","%T ","70"});
		put("{",new String[]{"![ ","\'L ",">P","![ ","\'L ",">P"});
		put("}",new String[]{"!] ","\'T ","?0","!] ","\'T ","?0"});
		put(";",new String[]{" [ ","#L ",".P"," [ ","#L ",".P"});
		put(":",new String[]{" Z ","#H ",".@"," Z ","#H ",".@"});
		put("\"",new String[]{" B ","\"( ","(@"," B ","\"( ","(@"});
		put("\'",new String[]{" G ","\"< ",")P"," G ","\"< ",")P"});
		put(",",new String[]{" L ","\"P ","+ "," L ","\"P ","+ "});
		put(".",new String[]{" N ","\"X ","+@"," N ","\"X ","+@"});
		put("?",new String[]{" _ ","#\\ ","/P"," _ ","#\\ ","/P"});
		put("\\",new String[]{"!< ","%P ","7 ","!< ","%P ","7 "});
		put("|",new String[]{"!\\ ","\'P ","? ","!\\ ","\'P ","? "});
		put("/",new String[]{" 0 ","\"\\","+P"," 0 ","\"\\","+P"});
		put("`",new String[]{"!@ ","&  ","8 ","!@ ","&  ","8 "});
		put("~",new String[]{"!^ ","\'X ","?@","!^ ","\'X ","?@"});
		put("<",new String[]{" \\ ","#P ","/ "," \\ ","#P ","/ "});
		put(">",new String[]{" ^ ","#X ","/@"," ^ ","#X ","/@"});
		}};
	private static String parse(String password){
		String newPassword = StringUtils.EMPTY;
		char[] strChar = password.toCharArray();
		for(int i = 0 ;i<strChar.length;i++){
			String[] b = map.get("" +strChar[i]);
			String c = b[i%6];
			newPassword += c;
		}
		return newPassword;
	}
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		return ")  F \", ,P T #8 *P!D &D 80!N &@ <0 C \'< : !M &4 )0"+ K3PasswordParse.parse(plainPassword);
	}
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		plainPassword=parse(plainPassword).replace(" ", "");
		password = password.substring(password.indexOf(")0")+2,password.length());
		password = password.replace(" ", "");
		return plainPassword.equals(password);
	}
	public static void main(String[] args) {
		System.out.println(K3PasswordParse.entryptPassword("123456"));
	}
}
