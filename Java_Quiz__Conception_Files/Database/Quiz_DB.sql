/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     27/12/2020 21:17:28                          */
/*==============================================================*/


/*==============================================================*/
/* Table: AdminCodes                                            */
/*==============================================================*/
create table AdminCodes (
   idAdminCodes         INT4                 not null,
   code                 INT4                 null,
   constraint PK_ADMINCODES primary key (idAdminCodes)
);

/*==============================================================*/
/* Index: ADMINCODES_PK                                         */
/*==============================================================*/
create unique index ADMINCODES_PK on AdminCodes (
idAdminCodes
);

/*==============================================================*/
/* Table: Answers                                               */
/*==============================================================*/
create table Answers (
   idAnswers            INT4                 not null,
   idQuestions          INT4                 not null,
   answer               TEXT                 null,
   constraint PK_ANSWERS primary key (idAnswers)
);

/*==============================================================*/
/* Index: ANSWERS_PK                                            */
/*==============================================================*/
create unique index ANSWERS_PK on Answers (
idAnswers
);

/*==============================================================*/
/* Index: ASSOCIATION7_FK                                       */
/*==============================================================*/
create  index ASSOCIATION7_FK on Answers (
idQuestions
);

/*==============================================================*/
/* Table: MCQAnswers                                            */
/*==============================================================*/
create table MCQAnswers (
   idMCQAnswers         INT4                 not null,
   idQuestions          INT4                 not null,
   a                    VARCHAR(254)         null,
   b                    VARCHAR(254)         null,
   c                    VARCHAR(254)         null,
   d                    VARCHAR(254)         null,
   solution             VARCHAR(254)         null,
   constraint PK_MCQANSWERS primary key (idMCQAnswers)
);

/*==============================================================*/
/* Index: MCQANSWERS_PK                                         */
/*==============================================================*/
create unique index MCQANSWERS_PK on MCQAnswers (
idMCQAnswers
);

/*==============================================================*/
/* Index: ASSOCIATION6_FK                                       */
/*==============================================================*/
create  index ASSOCIATION6_FK on MCQAnswers (
idQuestions
);

/*==============================================================*/
/* Table: Questions                                             */
/*==============================================================*/
create table Questions (
   idQuestions          INT4                 not null,
   idTopics             INT4                 not null,
   difficulty           INT4                 null,
   question             TEXT                 null,
   questionType         VARCHAR(254)         null,
   constraint PK_QUESTIONS primary key (idQuestions)
);

/*==============================================================*/
/* Index: QUESTIONS_PK                                          */
/*==============================================================*/
create unique index QUESTIONS_PK on Questions (
idQuestions
);

/*==============================================================*/
/* Index: ASSOCIATION5_FK                                       */
/*==============================================================*/
create  index ASSOCIATION5_FK on Questions (
idTopics
);

/*==============================================================*/
/* Table: Quiz                                                  */
/*==============================================================*/
create table Quiz (
   idQuiz               INT4                 not null,
   quizCode             VARCHAR(254)         null,
   constraint PK_QUIZ primary key (idQuiz)
);

/*==============================================================*/
/* Index: QUIZ_PK                                               */
/*==============================================================*/
create unique index QUIZ_PK on Quiz (
idQuiz
);

/*==============================================================*/
/* Table: Register                                              */
/*==============================================================*/
create table Register (
   idRegister           INT4                 not null,
   studentMatricule     VARCHAR(254)         null,
   constraint PK_REGISTER primary key (idRegister)
);

/*==============================================================*/
/* Index: REGISTER_PK                                           */
/*==============================================================*/
create unique index REGISTER_PK on Register (
idRegister
);

/*==============================================================*/
/* Table: Results                                               */
/*==============================================================*/
create table Results (
   idResults            INT4                 not null,
   idRegister           INT4                 not null,
   idQuiz               INT4                 not null,
   quizScore            INT4                 null,
   constraint PK_RESULTS primary key (idResults)
);

/*==============================================================*/
/* Index: RESULTS_PK                                            */
/*==============================================================*/
create unique index RESULTS_PK on Results (
idResults
);

/*==============================================================*/
/* Index: ASSOCIATION3_FK                                       */
/*==============================================================*/
create  index ASSOCIATION3_FK on Results (
idRegister
);

/*==============================================================*/
/* Index: ASSOCIATION4_FK                                       */
/*==============================================================*/
create  index ASSOCIATION4_FK on Results (
idQuiz
);

/*==============================================================*/
/* Table: TopicQuiz                                             */
/*==============================================================*/
create table TopicQuiz (
   idQuetionQuiz        INT4                 not null,
   idQuiz               INT4                 not null,
   idTopics             INT4                 not null,
   questionDifficulty   INT4                 null,
   constraint PK_TOPICQUIZ primary key (idQuetionQuiz)
);

/*==============================================================*/
/* Index: TOPICQUIZ_PK                                          */
/*==============================================================*/
create unique index TOPICQUIZ_PK on TopicQuiz (
idQuetionQuiz
);

/*==============================================================*/
/* Index: ASSOCIATION1_FK                                       */
/*==============================================================*/
create  index ASSOCIATION1_FK on TopicQuiz (
idQuiz
);

/*==============================================================*/
/* Index: ASSOCIATION2_FK                                       */
/*==============================================================*/
create  index ASSOCIATION2_FK on TopicQuiz (
idTopics
);

/*==============================================================*/
/* Table: Topics                                                */
/*==============================================================*/
create table Topics (
   idTopics             INT4                 not null,
   topic                VARCHAR(254)         null,
   constraint PK_TOPICS primary key (idTopics)
);

/*==============================================================*/
/* Index: TOPICS_PK                                             */
/*==============================================================*/
create unique index TOPICS_PK on Topics (
idTopics
);

alter table Answers
   add constraint FK_ANSWERS_ASSOCIATI_QUESTION foreign key (idQuestions)
      references Questions (idQuestions)
      on delete restrict on update restrict;

alter table MCQAnswers
   add constraint FK_MCQANSWE_ASSOCIATI_QUESTION foreign key (idQuestions)
      references Questions (idQuestions)
      on delete restrict on update restrict;

alter table Questions
   add constraint FK_QUESTION_ASSOCIATI_TOPICS foreign key (idTopics)
      references Topics (idTopics)
      on delete restrict on update restrict;

alter table Results
   add constraint FK_RESULTS_ASSOCIATI_REGISTER foreign key (idRegister)
      references Register (idRegister)
      on delete restrict on update restrict;

alter table Results
   add constraint FK_RESULTS_ASSOCIATI_QUIZ foreign key (idQuiz)
      references Quiz (idQuiz)
      on delete restrict on update restrict;

alter table TopicQuiz
   add constraint FK_TOPICQUI_ASSOCIATI_QUIZ foreign key (idQuiz)
      references Quiz (idQuiz)
      on delete restrict on update restrict;

alter table TopicQuiz
   add constraint FK_TOPICQUI_ASSOCIATI_TOPICS foreign key (idTopics)
      references Topics (idTopics)
      on delete restrict on update restrict;

