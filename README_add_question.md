# How to add a new question type to the code
Spring app, create a moodle xml format from excel file, and convert an existing xml to base64 coded xml

This is an instruction how you can add a new question type to the code.
  <h1>1. Create a class with a name of your new questiontype!</h1><br>
        <p>- Go to the <b>'com.edti.exceltoxml.Models.QuestionTypes'</b> package, and create your class with a name of the new question type!</p>
        <p>- Extend the RealQuestion class!</p>
        <p>- Implement the required methods!</p>
        <p>- The required injections: </p>
              <ul>
                <li>FieldProperties</li>
                <li>IStateService</li>
                <li>IImageService</li>
               </ul>
        <p>- In the constructor make a logic for the optional image generation, based on the state service! (The constructor will be used in the factory class)</p>
        <p>- Insert the services below, and annotate them with <b>@XmlTransient</b> annotation, to exclude them from the final xml.</p>
        
  <h1>2. Create an enum in the package <b>'com.edti.exceltoxml.Models.Enums'</b>, wich represents the name of the new question type!</h1><br>
        
  <h1>3. Add the question type to the factory</h1><br>
        <p>- You can find the factory in the <b>'com.edti.exceltoxml.Models.Factories'</b> package. </p>
        <p>- In the getQuestion method add your new question type, and use the constructor tompass the data map, and the autowired injections.</p>
        
  <h1>4. Create a new question provider class!</h1><br>
        <p>- In the <b>'com.edti.exceltoxml.Services.QuestionObjectProviders'</b> package, create a new question provider class. </p>
        <p>- Annotate it with a <b>@Service<b> annotation!</p>
        <p>- Extend the class from abstract class QuestionObjectProvider! </p>
        <p>- Implement the required methods!</p>
        
  <h1>5. Add the question to the question service!</h1><br>
        <p>- Inject the new question provider service into the QuestionService class!</p>
        <p>- In the createQuestionList method extract the switch-case with a copy of the new questions's name of the excel sheet! </p>
    
