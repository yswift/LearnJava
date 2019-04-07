package springioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextEditor {
	@Autowired
	private SpellChecker spellChecker;

	public TextEditor() {
		System.out.println("Inside TextEditor constructor.");
	}

	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}
