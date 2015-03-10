package intcalc;


import edu.hendrix.grambler.ParseException;
import edu.hendrix.grambler.Tree;

public class Evaluator {
	private Grammar grammar = new Grammar();
	
	public Evaluator() {
	}
	
	public String eval(String input) throws ParseException {
		String cleanedInput = removeWhiteSpaces(input);
		Tree t = grammar.parse(cleanedInput);
		return Integer.toString(evalTree(t));
	}

	private int evalTree(Tree t) {
		int x = 0;
		int y = 0;
		if (t.isNamed("num")) {
			return Integer.parseInt(t.toString());
		}else if(t.isNamed("lines")){
			return evalTree(t.getNamedChild("line"));
		} else  if (t.isNamed("line")){
			return evalTree(t.getNamedChild("expr"));
		} else if (t.isNamed("expr")){
			if(t.getNumChildren()== 1){
				return evalTree(t.getChild(0));
			}else{ if (t.hasNamed("op")){
				return evalTree(t.getChild(0)) + evalTree(t.getChild(2)) ;
			}else{return evalTree(t.getChild(0)) - evalTree(t.getChild(2));}
		}
		}
		
		
		else if (t.isNamed("expr2")){
			if(t.getNumChildren()== 1){
				return evalTree(t.getChild(0));
			}else{ if (t.hasNamed("op2")){
				return evalTree(t.getNamedChild("expr2")) * evalTree(t.getChild(2));
			}else{return evalTree(t.getNamedChild("expr2")) / evalTree(t.getChild(2));}
			}
		}
		
		
		
		
		else if (t.isNamed("paren")){
			if(t.getNumChildren()== 1){
				return evalTree(t.getChild(0));
			}else{return evalTree(t.getNamedChild("expr"));
			}
		}
		
		else if (t.isNamed("equality")){
			if (t.getChild(0).getName() == "x"){x=evalTree(t.getNamedChild("expr3"));
			return x;
			}else{y =evalTree(t.getNamedChild("expr3")); return y; }
		}
		
		
		else if (t.isNamed("expr3")){
			if(t.getNumChildren()== 1){
				return evalTree(t.getChild(0));
			}else{if (t.hasNamed("op")){
				return evalTree(t.getChild(0)) + evalTree(t.getChild(2));}
			else if (t.hasNamed("op1")){
				return evalTree(t.getChild(0)) - evalTree(t.getChild(2));}
			else if (t.hasNamed("op2")){
				return evalTree(t.getChild(0)) * evalTree(t.getChild(2));}
			else if (t.hasNamed("op3")){
				return evalTree(t.getChild(0)) / evalTree(t.getChild(2));}
			}
		}
		else if (t.isNamed("sy")){
			if (t.toString() == "x"){
				return x;
			}else{return y;}
		}
		
		
			
		else {throw new IllegalArgumentException("What is \""+ t.getName()+ "\"");
		}
		return 0;
	}
	
	
	private String removeWhiteSpaces(String input){
		return input.replaceAll("\\s+","");
	}

}
