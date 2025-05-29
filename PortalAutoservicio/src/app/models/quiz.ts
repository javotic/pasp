import { QuizConfig } from './quiz-config';
import { QuestionsKPI } from './questions';

export class Quiz {
    id: number;
    name: string;
    description: string;
    config: QuizConfig;
    questions: QuestionsKPI[];

    constructor(data: any) {
        if (data) {
            this.id = data.id;
            this.name = data.name;
            this.description = data.description;
            this.config = new QuizConfig(data.config);
            this.questions = [];
            data.questions.forEach(q => {
                //this.questions.push(new QuestionsKPI(q));
            });
        }
    }
}
