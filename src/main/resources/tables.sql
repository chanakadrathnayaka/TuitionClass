CREATE TABLE Users (
  userId       int auto_increment not null,
  name         varchar(255)       not null,
  email        varchar(100)       not null,
  username     varchar(20) unique not null,
  password     varchar(20)        not null,
  userType     varchar(10)        not null,
  enrolledDate date               not null,
  birthDate    date,
  primary key (userId)
);

CREATE TABLE Students (
  studentId int auto_increment not null,
  grade     int                not null,
  userId    int                not null,
  school    varchar(255),
  primary key (studentId),
  foreign key (userId) references Users (userId)
);

CREATE TABLE Subjects (
  subjectId   int auto_increment not null,
  subjectName varchar(255)       not null,
  primary key (subjectId)
);

CREATE TABLE Tutors (
  tutorId       int auto_increment      not null,
  userId        int unique              not null,
  qualification varchar(255),
  primary key (tutorId),
  foreign key (userId) references Users (userId)
);

CREATE TABLE Fees (
  feeId     int auto_increment unique not null,
  subjectId int                       not null,
  grade     int                       not null,
  tutorId   int                       not null,
  amount    bigint                    not null default 0,
  classType varchar(20)               not null,
  primary key (tutorId, grade, subjectId, classType),
  foreign key (subjectId) references Subjects (subjectId),
  foreign key (tutorId) references Tutors (tutorId)
);

CREATE TABLE FeePayment (
  feeId       int  not null,
  studentId   int  not null,
  paid        varchar(5) default 0,
  paymentDate date not null,
  foreign key (feeId) references Fees (feeId),
  foreign key (studentId) references Students (studentId)
);

CREATE TABLE TutorClasses (
  tutorId   int not null,
  subjectId int not null,
  feeId     int not null,
  grade     int not null,
  primary key (tutorId, feeId),
  foreign key (tutorId) references Tutors (tutorId),
  foreign key (feeId) references Fees (feeId),
  foreign key (subjectId) references Subjects (subjectId)
);

CREATE TABLE StudentEnroll (
  studentId int not null,
  tutorId   int not null,
  subjectId int not null,
  feeId     int not null,
  primary key (studentId, feeId),
  foreign key (tutorId) references Tutors (tutorId),
  foreign key (subjectId) references Subjects (subjectId),
  foreign key (feeId) references Fees (feeId),
  foreign key (studentId) references Students (studentId)
);