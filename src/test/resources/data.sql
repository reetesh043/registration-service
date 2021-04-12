SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE registration;
ALTER TABLE registration ALTER COLUMN id RESTART WITH 1;

INSERT INTO registration(username, password, dob, PAYMENT_CARD_NUMBER)
VALUES('Reetesh', 'Kumar12', PARSEDATETIME('1990-02-1990', 'yyyy-mm-dd'), '4123467891234567');
INSERT INTO registration(username, password, dob, PAYMENT_CARD_NUMBER)
VALUES('ReeteshKK', 'Kumar123', PARSEDATETIME('1990-02-1991', 'yyyy-mm-dd'), '4123467891234568');
INSERT INTO registration(username, password, dob, PAYMENT_CARD_NUMBER)
VALUES('ReeteshKR', 'Kumar124', PARSEDATETIME('1990-02-1991', 'yyyy-mm-dd'), '4123467891234569');