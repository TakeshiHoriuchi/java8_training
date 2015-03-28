package chapter8.ex13;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/*
TODO: テンプレートエンジン (Velocity)に食わせて.javaファイルを生成し、javax.tools.JavaCompilerでコンパイルする
*/

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"chapter8.ex13.TestCase", "chapter8.ex13.TestCases"})
public class TestProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Messager messager = processingEnv.getMessager();
    annotations.forEach(annotation -> {
      roundEnv.getElementsAnnotatedWith(annotation).forEach(elem -> {
        messager.printMessage(Diagnostic.Kind.NOTE, annotation.toString() + " " + elem.toString());
      });
    });
    
    return true;
  }
}
