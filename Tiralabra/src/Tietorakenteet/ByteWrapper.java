package Tietorakenteet;

import java.util.Arrays;

/**
 * Motivaatio: Byte []-array vie 12 tavua + 9*koko tavua muistia (12 tavua
 * arraylle, 8 tavua per Byte-objekti, 1 tavu bytelle) lisäksi omassa
 * ArrayList-toteutuksessa on datan koko + hashcode + boolean muutokselle
 * tallennettu -> 5 tavua lisää -> overhead OmaArrayList<Byte> - listalle on 12
 * + 5 + 8*arrayn koko (ei tallennetun tiedon koko) tavua = 19 + 8*koko-tavua.
 *
 * Blokkeja tehdessä käytin Omalist<omalist<byte>>-rakennetta -> hirveästi
 * muistia hukkaan
 *
 * Tämän overhead: 8 tavua bytewrapperille + taulukonkoko -> 8 + oikea koko vs
 * worst case 19 + 9*(oikea koko*2) Hash code tarvitaan että voidaan käyttää
 * indeksinä hashmapille
 */
public class ByteWrapper {

    public byte[] byteTaulukko;

    /**
     * Apumetodi. Palauttaa taulukon koon. wrapper.size() on mielyttävämpi kuin
     * wrapper.byteTaulukko.length
     *
     * @return
     */
    public int size() {
        return byteTaulukko.length;
    }

    @Override
    public int hashCode() {
         int hashCode = 0;
         for (int i = 0; i < byteTaulukko.length; ++i) {
         hashCode ^= (hashCode << 5) + (hashCode >> 2) + byteTaulukko[i];
         }

         return hashCode;
    }

    @Override
    public boolean equals(Object obj) {


        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        if (size() != this.size()) {
            return false;
        }

        final ByteWrapper other = (ByteWrapper) obj;
        if (hashCode() != other.hashCode()) {
            return false;
        }

        if (!Arrays.equals(this.byteTaulukko, other.byteTaulukko)) {
            return false;
        }

        return true;
    }
}