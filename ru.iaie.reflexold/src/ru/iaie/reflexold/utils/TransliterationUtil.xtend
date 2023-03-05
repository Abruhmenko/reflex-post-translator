package ru.iaie.reflexold.utils

class TransliterationUtil {
	
	def static String ru2en(int charAsInt) {
		switch charAsInt {
			case 1040: return "A"
			case 1041: return "B"
			case 1042: return "V"
			case 1043: return "G"
			case 1044: return "D"
			case 1045: return "E"
			case 1025: return "YO"
			case 1046: return "ZH"
			case 1047: return "Z"
			case 1048: return "I"
			case 1049: return "J"
			case 1050: return "K"
			case 1051: return "L"
			case 1052: return "M"
			case 1053: return "N"
			case 1054: return "O"
			case 1055: return "P"
			case 1056: return "R"
			case 1057: return "S"
			case 1058: return "T"
			case 1059: return "U"
			case 1060: return "F"
			case 1061: return "H"
			case 1062: return "TS"
			case 1063: return "CH"
			case 1064: return "SH"
			case 1065: return "SH"
			case 1066: return "Q"
			case 1067: return "Y"
			case 1068: return "Q"
			case 1069: return "E"
			case 1070: return "YU"
			case 1071: return "YA"
			
			case 1072: return "a"
			case 1073: return "b"
			case 1074: return "v"
			case 1075: return "g"
			case 1076: return "d"
			case 1077: return "e"
			case 1105: return "yo"
			case 1078: return "zh"
			case 1079: return "z"
			case 1080: return "i"
			case 1081: return "j"
			case 1082: return "k"
			case 1083: return "l"
			case 1084: return "m"
			case 1085: return "n"
			case 1086: return "o"
			case 1087: return "p"
			case 1088: return "r"
			case 1089: return "s"
			case 1090: return "t"
			case 1091: return "u"
			case 1092: return "f"
			case 1093: return "h"
			case 1094: return "ts"
			case 1095: return "ch"
			case 1096: return "sh"
			case 1097: return "sh"
			case 1098: return "q"
			case 1099: return "y"
			case 1100: return "q"
			case 1101: return "e"
			case 1102: return "yu"
			case 1103: return "ya"
			default: return Character.toString(charAsInt)
		}
	}
	
	def static String transliterate(String str) {
		var newStr = ""
		for (char ch : str.toCharArray) {
			newStr += ru2en(ch)
		}
		return newStr
	}
}